package com.example.demo.controller;

import com.example.demo.model.DemoResponse;
import com.example.demo.model.DemoResponseCode;
import com.example.demo.model.NotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.util.List;

import static java.util.stream.Collectors.joining;

@Api(value = "接口名称",tags = "用户操作接口")
@Validated
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 根据ID获取用户
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详细信息",notes = "根据用户的id获取详细信息")
    @ApiImplicitParam(name = "id", value = "用户的ID", required = true, dataType = "int", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 200, message = "正常响应"),
            @ApiResponse(code = 400, message = "请求有语法错误")
    })
    @GetMapping("user/{id}")
    public DemoResponse getUserById(@Min(1) @PathVariable(value = "id") Integer id){
        User user = userService.findOne(id);
        if(user == null){
            throw new NotFoundException();
        }
        return new DemoResponse(user);
    }

//    /**
//     * 查询用户列表
//     * @return
//     */
//    @ApiOperation(value = "获取所有用户信息" ,notes = "获取所有用户的详细信息")
//    @GetMapping("users")
//    public DemoResponse getUserList(){
//        List<User> list = userService.findAll();
//        if(list.size() == 0){
//            throw new NotFoundException();
//        }
//        return new DemoResponse(list);
//    }

    /**
     * 添加用户
     * @return
     */
    @ApiOperation(value = "添加用户的详细信息",notes = "添加用户的详细信息")
    @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    @PostMapping("user")
    public DemoResponse add(@Validated @RequestBody User user,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String error = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(joining());
            return new DemoResponse(DemoResponseCode.CODE_112.getCode(),error,null);
        }

        userService.create(user);

        return new DemoResponse(DemoResponseCode.CODE_0.getCode(),"添加成功",user);
    }

    /**
     * 修改用户
     * @param id
     * @param user
     * @return
     */
    @ApiOperation(value = "修改用户的详细信息", notes = "修改用户的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的ID", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户信息", required = true, dataType = "User")
    })
    @PutMapping("user/{id}")
    public DemoResponse update(@PathVariable(value = "id") Integer id, @Validated @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String error = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(joining());
            return new DemoResponse(DemoResponseCode.CODE_112.getCode(),error,null);
        }

        userService.update(user);

        return new DemoResponse(DemoResponseCode.CODE_0.getCode(),"修改成功",user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户的详细信息", notes = "删除用户的详细信息")
    @ApiImplicitParam(name = "删除用户", value = "删除用户的详细信息", required = true, dataType = "int", paramType = "path")
    @DeleteMapping("user/{id}")
    public DemoResponse delete(@Max(3000) @PathVariable(value = "id") Integer id){

        userService.delete(id);

        return new DemoResponse(DemoResponseCode.CODE_0.getCode(),"删除成功",id);
    }

    /**
     * Swagger忽略
     * @return
     */
    @ApiIgnore
    @GetMapping("ignore")
    public String ignoreApi(){
        return "this url will be ignored at api";
    }
}
