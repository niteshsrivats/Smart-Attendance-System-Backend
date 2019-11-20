package attendance.system.central.controller;

import attendance.system.central.models.entities.Device;
import attendance.system.central.models.payload.JwtAuthenticationResponse;
import attendance.system.central.models.payload.LoginRequest;
import attendance.system.central.named.Endpoints;
import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@RestController
@CrossOrigin
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

    @RequestMapping(Endpoints.Devices.Base)
    @GetMapping
    public List<Device> getDevices() {
        return deviceService.getDevices();
    }

    @RequestMapping(Endpoints.Devices.GetById)
    @GetMapping
    public Device getDeviceById(@PathVariable @NotBlank String id) {
        return deviceService.getDeviceById(id);
    }

    @RequestMapping(Endpoints.Devices.Signup)
    @PostMapping
    public Device registerDevice(@RequestBody @Valid @NotNull Device device) {
        return deviceService.addDevice(device);
    }

    @RequestMapping(Endpoints.Devices.Signin)
    @PostMapping
    public JwtAuthenticationResponse authenticateDevice(@RequestBody @Valid @NotNull LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getId(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt);
    }
}
