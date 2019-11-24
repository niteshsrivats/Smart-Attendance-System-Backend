package attendance.system.central.controller;

import attendance.system.central.models.entities.Device;
import attendance.system.central.models.entities.Section;
import attendance.system.central.models.payload.JwtAuthenticationResponse;
import attendance.system.central.models.payload.LoginRequest;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.security.UserPrincipal;
import attendance.system.central.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
public class DeviceController {
    private final DeviceService deviceService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public DeviceController(DeviceService deviceService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.deviceService = deviceService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping(Endpoints.Devices.Base)
    public Device getDevice(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return deviceService.getDevice(userPrincipal.getUsername());
    }

    @PostMapping(Endpoints.Devices.Signup)
    public Device registerDevice(@RequestBody @Valid @NotNull Device device) {
        return deviceService.addDevice(device);
    }

    @PostMapping(Endpoints.Devices.Signin)
    public JwtAuthenticationResponse authenticateDevice(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }

    @PatchMapping(Endpoints.Devices.Sections)
    public Device updateDeviceSection(@AuthenticationPrincipal UserPrincipal userPrincipal,
                                      @RequestBody @Valid @NotNull Section section) {
        return deviceService.updateSection(userPrincipal.getUsername(), section);
    }
}
