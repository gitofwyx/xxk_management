package com.xxk.management.user.controller;

import com.xxk.core.file.BaseController;
import com.xxk.core.util.DateUtil;
import com.xxk.core.util.PrivacyUtil;
import com.xxk.core.util.UUIdUtil;
import com.xxk.management.roles.entity.Roles;
import com.xxk.management.roles.service.RolesService;
import com.xxk.management.user.entity.RegUser;
import com.xxk.management.user.service.RebUserService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/15.
 */
@Controller
@RequestMapping("")
public class RegUserController extends BaseController {

    private static Logger log = Logger.getLogger(RegUserController.class);

    @Autowired
    private RebUserService rebUserService;

    @Autowired
    private RolesService rolesService;

    @ResponseBody
    @RequestMapping("/listRegUser")
    public Map<String, Object> listRegUser(@RequestParam(value = "pageIndex") String pageIndex,
                                           @RequestParam(value = "limit") String limit,
                                           @RequestParam(value = "account") String account,
                                           @RequestParam(value = "name") String name,
                                           @RequestParam(value = "startDate") String startDate,
                                           @RequestParam(value = "startDate") String endDate) {
        Map<String, Object> result = new HashMap<>();
        try {
            int pageNumber = Integer.parseInt(pageIndex) + 1;//因为pageindex 从0开始
            int pageSize = Integer.parseInt(limit);

            List<RegUser> listRegUser = rebUserService.listRegUser(pageNumber, pageSize);
            if (listRegUser == null) {
                log.error("数据为空");
                result.put("hasError", true);
                result.put("error", "数据为空");
                return result;
            } else {
                result.put("rows", listRegUser);
                result.put("results", rebUserService.countRegUser());
            }
        } catch (Exception e) {
            log.error(e);
            result.put("hasError", true);
            result.put("error", "获取数据出错");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/addRegUser")
    public Map<String, Boolean> addRegUser(RegUser user) {
        Map<String, Boolean> result = new HashMap<>();
        String createDate = DateUtil.getFullTime();
        String id = UUIdUtil.getUUID();
        try {
            user.setRealName(user.getName());
            user.setName(PrivacyUtil.replaceNameX(user.getName()));
            user.setId(id);
            user.setPassword("123");
            user.setCreateDate(createDate);
            user.setCreateUserId(id);
            user.setUpdateDate(createDate);
            user.setUpdateUserId(id);
            user.setDeleteFlag("0");

            boolean Result = rebUserService.addRegUser(user);
            if (!(Result)) {
                result.put("error", false);
            } else {
                result.put("success", true);
            }
        } catch (Exception e) {
            result.put("error", false);
            log.info(e);
        }
        return result;
        //return "system/index";
    }

    @ResponseBody
    @RequestMapping("/getRegUser")
    public Map<String, Object> getRegUser(@RequestParam(value = "id", required = false) String id) {
        if(id==null||"".equals(id)||"null".equals(id)){
            Subject currentUser = SecurityUtils.getSubject();
            Session session = currentUser.getSession();
            id = (String) session.getAttribute("userId");
        }
        Map<String, Object> result = new HashMap<>();
        try {
            RegUser user = rebUserService.getRegUser(id);
            if (user == null) {
                log.error("获取出错，对象为空！");
                result.put("error", false);
                return result;
            }
            result.put("Object", user);
            result.put("success", true);
        } catch (Exception e) {
            log.error(e);
            result.put("error", false);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping("/updateRegUser")
    public Map<String, Boolean> updateRegUser(RegUser user) {
        Map<String, Boolean> result = new HashMap<>();
        //String phone=(String)request.getAttribute("phone");
        //String phone = request.getParameter("phone");
        String updateDate = DateUtil.getFullTime();
        try {
            user.setUpdateUserId("admin");
            user.setUpdateDate(updateDate);
            if (rebUserService.updateRegUser(user) == false) {
                log.error("更新出错");
                result.put("success", true);
                return result;
            }
            result.put("success", true);
        } catch (Exception e) {
            log.error(e);
            result.put("error", false);
        } finally {
            return result;
        }
    }

    @ResponseBody
    @RequestMapping("/delRegUserOfLogic")
    public Map<String, Boolean> delRegUserOfLogic(@RequestParam("ids[]") List<String> ids) {
        Map<String, Boolean> result = new HashMap<>();
        try {
            if (rebUserService.deleteListRegUser(ids) == false) {
                log.error("删除出错");
                result.put("error", false);
                return result;
            }
            result.put("success", true);
        } catch (Exception e) {
            log.error(e);
            result.put("error", false);
        } finally {
            return result;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/addRoles", method = RequestMethod.POST) // 添加角色信息
    public Map<String, String> addRoles(HttpServletRequest request) {
        // Subject currentUser = SecurityUtils.getSubject();
        Map<String, String> result = new HashMap<>();
        Roles role = new Roles();
        String createDate = DateUtil.getFullTime();
        String id = UUIdUtil.getUUID();
        try {
            String roleVal = "admin";
            role.setId(id);
            role.setRoleVal(roleVal);
            role.setDeleteFlag("0");
            role.setCreateUserId("admin");
            role.setCreateDate(createDate);
            role.setUpdateUserId("admin");
            role.setUpdateDate(createDate);
            if (rolesService.addUserRole(role)) {
                result.put("0", "添加角色成功");
                log.info("添加角色成功");
            } else {
                result.put("1", "添加角色失败");
                log.error("添加角色失败");
            }
        }
        // int x=Integer.parseInt(s); //把得到字符串转化为整型
        catch (Exception e) {
            System.out.println(e);
            log.error(">>>>添加角色失败");
            result.put("isError", "1");
            result.put("1", "添加角色失败");
            return result;
        }
        return result;
    }

    @RequestMapping("/playUser")
    public ModelAndView  userView() {
        Map<String, Object> result = new HashMap<>();
        return new ModelAndView("/detail/example", "result", result);
    }

    @ResponseBody
    @RequestMapping(value = "/addRegUserTest",method = RequestMethod.GET)
    public Map<String, Boolean> addRegUserTest(RegUser user) {
        Map<String, Boolean> result = new HashMap<>();
        String createDate = DateUtil.getFullTime();
        String id = UUIdUtil.getUUID();
        try {
            user.setId(id);
            user.setPassword("123");
            user.setCreateDate(createDate);
            user.setCreateUserId(id);
            user.setUpdateDate(createDate);
            user.setUpdateUserId(id);
            user.setDeleteFlag("0");

            boolean Result = rebUserService.addTest(user);
            if (!(Result)) {
                result.put("error", false);
            } else {
                result.put("success", true);
            }
        } catch (Exception e) {
            result.put("error", false);
            log.info(e);
        }
        return result;
        //return "system/index";
    }
}
