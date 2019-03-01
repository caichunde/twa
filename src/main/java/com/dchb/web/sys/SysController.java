package com.dchb.web.sys;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dchb.config.MD5Util;
import com.dchb.model.agency.B00;
import com.dchb.model.turn.B1A;
import com.dchb.service.agency.AgencyB00Service;
import com.dchb.service.turn.B1AService;
import com.dchb.util.BaseController;
import com.dchb.util.IReturn;
import com.dchb.util.RSAUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @param @param
 * @author hukai
 * @Title: ()
 * @Description: TODO()
 * @return ReturnMsg    返回类型
 * @date date上午11:24:23
 */
@Controller
@RequestMapping("twa")
public class SysController extends BaseController {
    @Autowired
    private B1AService b1AService;
    @Autowired
    private AgencyB00Service agencyB00Service;

    /**
     * @param @param
     * @return ReturnMsg    返回类型
     * @Title: (一键通跳转登录验证接口)
     * @Description: TODO(userB1ALogin)
     * @author caichunde
     * @date 2019年1月22日
     */
    @RequestMapping(method = RequestMethod.POST, value = "/pttLogin")
    @ResponseBody
    public IReturn pttLogin(@RequestParam("username") String username, @RequestParam("password") String password,
                            @RequestParam(value = "sfzhm") String sfzhm, HttpServletRequest request) {
        username = RSAUtils.decryptDataOnJava(username).replaceAll("\"", "");// RSA解密替换掉双引号
        password = RSAUtils.decryptDataOnJava(password).replaceAll("\"", "");
        sfzhm = RSAUtils.decryptDataOnJava(sfzhm);
        JSONObject jsonObject = JSONObject.parseObject(sfzhm);
        sfzhm = jsonObject.getString("idNo").replaceAll("\"", "");
        IReturn msg = success();

        if (StringUtils.isEmpty(username)) {
            msg = fail("用户名不能为空。");
            return msg;
        }
        if (StringUtils.isEmpty(password)) {
            msg = fail("密码不能为空。");
            return msg;
        }
        if (StringUtils.isEmpty(sfzhm)) {
            msg = fail("身份证号码不能为空。");
            return msg;
        }
        QueryWrapper<B1A> wrapper = new QueryWrapper();
        wrapper.eq("pwd", password);
        wrapper.eq("acc", username);
        wrapper.eq("idNo", sfzhm);
        wrapper.eq("bEnabled", "1");
        B1A newUserB1A = b1AService.getOne(wrapper);
        if (null == newUserB1A) {
            msg = fail("用户名或密码错误");
            return msg;
        }
        String orgId = newUserB1A.getOrgid();

        B00 b00 = agencyB00Service.getOne(new QueryWrapper<B00>().eq("orgid", orgId));
        saveUserToSession(request, newUserB1A, b00);// 存入session

        return msg;
    }

    /**
     * @param @param
     * @return ReturnMsg    返回类型
     * @Title: (用户登陆)
     * @Description: TODO(userB1ALogin)
     * @author caichunde
     * @date 2018年11月21日
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ResponseBody
    public IReturn login(@RequestParam("username") String username, @RequestParam("password") String password,
                         @RequestParam(value = "sfzhm") String sfzhm, HttpServletRequest request) {
        IReturn msg = success();
        if (StringUtils.isEmpty(username)) {
            msg = fail("用户名不能为空。");
            return msg;
        }
        if (StringUtils.isEmpty(password)) {
            msg = fail("密码不能为空。");
            return msg;
        }
        if (StringUtils.isEmpty(sfzhm)) {
            msg = fail("身份证号码不能为空。");
            return msg;
        }
        QueryWrapper<B1A> wrapper = new QueryWrapper();
        wrapper.eq("pwd", password);
        wrapper.eq("acc", username);
        wrapper.eq("idNo", sfzhm);
        wrapper.eq("bEnabled", "1");
        B1A newUserB1A = b1AService.getOne(wrapper);
        if (null == newUserB1A) {
            msg = fail("用户名或密码错误");
            return msg;
        }
        String orgId = newUserB1A.getOrgid();

        B00 b00 = agencyB00Service.getOne(new QueryWrapper<B00>().eq("orgid", orgId));
        saveUserToSession(request, newUserB1A, b00);// 存入session

        return msg;
    }

    /**
     * @param @param
     * @return ReturnMsg    返回类型
     * @Title: (用户登陆)
     * @Description: TODO(userB1ALogin)
     * @author caichunde
     * @date 2018年11月21日
     */
    @RequestMapping(value = "/tranLogin")
    @ResponseBody
    public IReturn tranLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        IReturn msg = success();
        if (StringUtils.isEmpty(username)) {
            msg = fail("用户名不能为空。");
            return msg;
        }
        if (StringUtils.isEmpty(password)) {
            msg = fail("密码不能为空。");
            return msg;
        }
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        if (null != b1A) {
            msg = fail("对不起，同一浏览器只能同时登录一个账户。");
            return msg;
        }
        QueryWrapper<B1A> queryWrapper = new QueryWrapper();
        queryWrapper.eq("pwd", MD5Util.md5(password.trim()));
        queryWrapper.and(wrapper -> wrapper.eq("acc", username.trim())
                .or().eq("idNo", username.trim())
                .or().eq("wxId", username.trim()));
        queryWrapper.eq("bEnabled", "1");
        B1A newUserB1A = b1AService.getOne(queryWrapper);
        if (null == newUserB1A) {
            msg = fail("用户名或密码错误");
            return msg;
        }
        String orgId = newUserB1A.getOrgid();

        B00 b00 = agencyB00Service.getOne(new QueryWrapper<B00>().eq("orgid", orgId));
        saveUserToSession(request, newUserB1A, b00);// 存入session
        return msg;
    }

    /**
     * @param B1A b1a
     * @return
     * @Title: (saveSessionUser)
     * @Description: 用户基本信息放入session 登录后使用。
     * @author caichunde
     * @date 2018年11月7日
     */
    protected void saveUserToSession(HttpServletRequest request, B1A b1a, B00 b00) {
        HttpSession session = request.getSession();
        session.setAttribute("b1a", b1a);
        session.setAttribute("b00", b00);
    }

    /**
     * b1a用户表本地登出页面
     *
     * @param
     * @return ReturnMsg
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public IReturn logout(HttpServletRequest request) {
        IReturn msg = success();
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        if (null == b1A) {
            msg = fail("您尚未登录！");
        }
        session.removeAttribute("b1a");
        session.removeAttribute("b00");
        return msg;
    }

}
