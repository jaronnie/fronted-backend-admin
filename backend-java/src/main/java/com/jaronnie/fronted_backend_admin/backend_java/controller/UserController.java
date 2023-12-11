package com.jaronnie.fronted_backend_admin.backend_java.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.jaronnie.fronted_backend_admin.backend_java.domain.bo.LogUpBo;
import com.jaronnie.fronted_backend_admin.backend_java.domain.bo.LoginBo;
import com.jaronnie.fronted_backend_admin.backend_java.domain.bo.PageQuery;
import com.jaronnie.fronted_backend_admin.backend_java.domain.vo.LoginResponseVo;
import com.jaronnie.fronted_backend_admin.backend_java.domain.vo.TableDataInfo;
import com.jaronnie.fronted_backend_admin.backend_java.domain.vo.UserVo;
import com.jaronnie.fronted_backend_admin.backend_java.service.IUserService;
import com.jaronnie.fronted_backend_admin.backend_java.util.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1.0/user")
@Api(tags = "用户管理")
public class UserController {
    private final IUserService iUserService;

    @ApiOperation(value = "获取用户列表")
    @GetMapping("/list")
    @SaCheckLogin
    public R<TableDataInfo<UserVo>> getUsers(@ModelAttribute PageQuery pageQuery) {
        return R.ok(iUserService.queryPageList(pageQuery));
    }

    @ApiOperation(value = "添加用户")
    @PostMapping("/add")
    @SaCheckLogin
    public R<UserVo> add(@RequestBody LogUpBo logUpBo) {
        return R.ok(iUserService.logUp(logUpBo));
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public R<LoginResponseVo> login(@RequestBody LoginBo loginBo) {
        return R.ok(iUserService.login(loginBo));
    }

    @ApiOperation(value = "用户登出")
    @SaCheckLogin
    @GetMapping("/logout")
    public R<Void> logout() {
        iUserService.logout();
        return R.ok("ok", null);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping("/info")
    @SaCheckLogin
    public R<UserVo> info() {
        return R.ok(iUserService.info());
    }
}
