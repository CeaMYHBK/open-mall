package com.ceam.web.components.service;

import com.ceam.admin.dto.CeaMSysUserDTO;
import com.ceam.admin.service.ICeamSysRoleService;
import com.ceam.admin.service.ICeamSysUserService;
import com.ceam.admin.vo.JwtUser;
import com.ceam.common.constants.GlobalConstants;
import com.ceam.common.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author CeaM
 * 2023/01/18 22:41
 **/
@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ICeamSysUserService userInfoService;

    private final ICeamSysRoleService roleInfoService;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        CeaMSysUserDTO ceaMSysUserDTO = userInfoService.loadUserInfo(username);

        return createJwtUser(ceaMSysUserDTO);
    }

    private UserDetails createJwtUser(CeaMSysUserDTO user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getStatus() == GlobalConstants.ZERO,
                user.getNickname(),
                user.getAvatar(),
                roleInfoService.grantedAuthorities(user.getId())
        );
    }
}
