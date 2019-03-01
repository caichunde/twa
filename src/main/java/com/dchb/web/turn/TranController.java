package com.dchb.web.turn;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dchb.model.agency.B00;
import com.dchb.model.agency.B13;
import com.dchb.model.turn.*;
import com.dchb.model.validClass.ValidTran;
import com.dchb.service.agency.AgencyB00Service;
import com.dchb.service.agency.B13Service;
import com.dchb.service.turn.A01Service;
import com.dchb.service.turn.Icdninev4Service;
import com.dchb.service.turn.IcdtenService;
import com.dchb.service.turn.TranService;
import com.dchb.util.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 转出前端控制器
 *
 * @author caichunde
 * @since 2018-11-20
 */
@RestController
@RequestMapping("tran")
public class TranController extends BaseController {
    @Autowired
    private A01Service a01Service;
    @Autowired
    private TranService tranService;
    @Autowired
    private B13Service b13Service;
    @Autowired
    private AgencyB00Service agencyB00Service;
    @Autowired
    private IcdtenService icdtenService;
    @Autowired
    private Icdninev4Service icdninev4Service;

    /**
     * @param @param
     * @return ReturnMsg    返回类型
     * @Title: (获取登录用户所在机构信息)
     * @Description: TODO(getOrgInfo)
     * @author caichunde
     * @date 2019年1月8日
     */
    @RequestMapping(value = "/getOrgInfo", method = RequestMethod.POST)
    @ResponseBody
    public IReturn getOrgInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        String orgId = b1A.getOrgid();
        QueryWrapper<B00> wrapper = new QueryWrapper();
        wrapper.eq("orgId", orgId);
        wrapper.eq("bEnabled", "1");
        List<B00> zcjg = agencyB00Service.selectListB00(wrapper);// 获取转出机构信息

        QueryWrapper<B13> queryWrapper = new QueryWrapper();
        queryWrapper.eq("jg_dm", orgId);
        queryWrapper.eq("status", "1");
        List<B13> zcks = b13Service.selectListB13(queryWrapper);// 获取机构下转出科室信息

        HashMap<String, Object> map = new HashMap();// 放入map返回
        map.put("zcjg", zcjg);//转出机构信息
        map.put("zcks", zcks);//转出科室信息
        IReturn iReturn = new IReturn(true, "返回值说明：zcjg-转出机构信息；zcks-转出科室信息；zrjgks-转入机构和科室信息", map);
        return iReturn;
    }

    /**
     * @param @param
     * @return ReturnMsg    返回类型
     * @Title: 获取转入机构列表
     * @Description: 获取转入机构列表
     * @author caichunde
     * @date 2018年11月20日
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getOrgList")
    @ResponseBody
    public IReturn getOrgList(String resource, HttpServletRequest request) {
        HttpSession session = request.getSession();
        B00 b00 = (B00) session.getAttribute("b00");
        HashMap<String, Object> map = new HashMap();// 放入map返回

        QueryWrapper<B00> wrapper = new QueryWrapper();
        wrapper.eq("bEnabled", "1");
        List<B00> listB00 = agencyB00Service.selectListB00(wrapper);// 获取所有机构信息
        int orgGrade = Integer.parseInt(b00.getOrgGrade());

        QueryWrapper<B13> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status", "1");
        List<B13> departmentB13List = b13Service.selectListB13(queryWrapper);///获取所有机构下科室信息

        List<B00> listB001 = new ArrayList<>();
        for (int i = 0, len = listB00.size(); i < len; i++) {
            B00 b001 = listB00.get(i);
            int orgGrade1 = Integer.parseInt(b001.getOrgGrade());
            List<B13> departmentB13One = departmentB13List.stream().filter(d -> {
                return d.getJgDm().equals(b001.getOrgId());
            }).collect(Collectors.toList());// 获取单个机构下科室信息
            b001.setZrksList(departmentB13One);
            if ("1".equals(resource)) {
                if (orgGrade < orgGrade1) {
                    listB001.add(b001);
                    continue;
                }
            }
            if ("0".equals(resource)) {
                if (orgGrade > orgGrade1) {
                    listB001.add(b001);
                    continue;
                }
            }
            if (orgGrade1 == 0) {
                listB001.add(b001);
            }
        }
        if (listB001.size() == 0) {
            return fail("无数据。");
        }
        map.put("zrjgks", listB001);//转入机构和科室信息
        IReturn iReturn = new IReturn(true, "返回值说明：zcjg-转出机构信息；zcks-转出科室信息；zrjgks-转入机构和科室信息", map);
        return iReturn;
    }

    /**
     * @param
     * @return ReturnMsg
     * @author caichunde
     * @Description: 根据身份证号码获取患者信息
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getA01One", method = RequestMethod.POST)
    @ResponseBody
    public IReturn getA01One(String sfzhm) {
        if (StringUtils.isEmpty(sfzhm)) {
            return new IReturn(false, "身份证号不能为空。");
        }
        QueryWrapper<A01> wrapper = new QueryWrapper();
        wrapper.eq("status", "1");
        wrapper.eq("sfzhm", sfzhm);
        A01 a01 = a01Service.getA01One(wrapper);
        if (null == a01) {
            return new IReturn(false, "未查到患者信息。");
        }
        return new IReturn(true, a01);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 保存转出信息
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/saveOrUpdateTran", method = RequestMethod.POST)
    @ResponseBody
    public IReturn saveOrUpdate(ValidTran validTran, HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        String id = validTran.getId();
        String out_org_id = validTran.getMechaniid();                 //转出机构id
        String out_org_name = validTran.getMechanism();                 //转出机构名称
        String out_department = validTran.getDepartment();                 //转出科室
//        String out_doctor_id = validTran.getDoctorId();                 //转出医生id
//        String out_doctor_name = validTran.getDoctorName();                 //转出医生姓名

        String out_doctor_phonenum = validTran.getDoctorPhone();                 //转出医生电话
        String tran_direct = validTran.getResource();                 //转出方向
        String sker_idcardno = validTran.getPatientIDcard();                 //患者身份证号
        String sker_name = validTran.getPatientName();                //患者姓名
        String sker_sex = validTran.getPatientSex();                 //患者性别

        String sker_phonenum = validTran.getPatientPhone();                 //患者电话
        String sker_content = validTran.getPatientTreatmentSituation();                //患者诊疗情况
        String sker_firstdia_code = validTran.getPatientDiagnosticCode();                 //患者初步诊断码
        String sker_firstdia_name = validTran.getPatientPreliminaryJudgment();                 //患者初步诊
//        String sker_report = validTran.getPatientAttachment();                 //患者诊疗附件

        String sker_tranreasion = validTran.getTranReason();                //转诊原因
        String in_org_id = validTran.getMechanismIntoId();                 //转入机构id
        String in_org_name = validTran.getMechanismInto();                 //转入机构名称
        String in_department = validTran.getDepartmentInto();              //转入科室
        String tran_state = validTran.getState();                 //转出状态

        Tran tran = new Tran();

        tran.setId(id);
        tran.setOutOrgId(out_org_id);
        tran.setInOrgId(in_org_id);
        tran.setTranState(tran_state);
        tran.setInDepartment(in_department);
        tran.setSkerContent(sker_content);

        tran.setSkerFirstdiaCode(sker_firstdia_code);
        tran.setSkerFirstdiaName(sker_firstdia_name);
        tran.setSkerPhonenum(sker_phonenum);
        tran.setSkerName(sker_name);
        tran.setSkerSex(sker_sex);

        tran.setSkerTranreasion(sker_tranreasion);
        tran.setSkerIdcardno(sker_idcardno);
        tran.setInOrgName(in_org_name);
        tran.setOutDepartment(out_department);
        tran.setOutDoctorId(String.valueOf(b1A.getId()));

        tran.setOutDoctorPhonenum(out_doctor_phonenum);
        tran.setOutDoctorName(b1A.getOname());
        tran.setOutOrgName(out_org_name);
        tran.setTranDirect(tran_direct);
        tran.setTranDate(new Date());

        MultipartFile[] files = validTran.getFiles();
        Map<String, String> resMap = new HashMap<>();
        if (!StringUtils.isEmpty(files)) {
            String mkdirs = out_org_id;
            if (StringUtils.isEmpty(mkdirs)) {
                mkdirs = "temp";
            }
            String path = DocFileUtil.getProjectPath();
            String filePath = path + mkdirs + File.separator + sker_idcardno + File.separator;
            DocFileUtil.createPath(filePath);
            resMap = DocFileUtil.batchUploadFiles(files, filePath, sker_idcardno);
            tran.setSkerReport(resMap.get("savePath"));
        }

        if (!StringUtils.isEmpty(id)) {
            QueryWrapper<Tran> wrapper = new QueryWrapper();
            wrapper.select("sker_report");
            wrapper.eq("id", id);
            Tran tranOne = tranService.getOne(wrapper);
            if (!StringUtils.isEmpty(files) && null != tranOne) {// 刪除附件信息
                String report = tranOne.getSkerReport();
                if (!StringUtils.isEmpty(report)) {
                    try {
                        DocFileUtil.deleteFile(report);
                    } catch (Exception e) {
                        logger.info("保存患者错误信息：" + e.getMessage());
                    }
                }
            }
        }
        boolean b = tranService.saveOrUpdate(tran);
        if (!b) {
            return new IReturn(false, "操作失败！", resMap.get("msg"));
        }
        return success();
    }

    /**
     * @param
     * @return ReturnMsg
     * @author caichunde
     * @Description: 根据id获取患者信息
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getTranOne", method = RequestMethod.POST)
    @ResponseBody
    public IReturn getTranOne(String id) {
        if (StringUtils.isEmpty(id)) {
            return fail("传入参数错误。");
        }
        QueryWrapper<Tran> wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        Tran tran = tranService.getOne(wrapper);
        if (null == tran) {
            return fail("未查到患者信息。");
        }
        String inOrgId = tran.getInOrgId();
        String inDepartment = tran.getInDepartment();
        QueryWrapper<B13> wrapperB13 = new QueryWrapper();
        wrapperB13.eq("jg_dm", inOrgId);
        wrapperB13.eq("ksmc", inDepartment);
        wrapperB13.eq("status", 1);
        List<B13> b13List = b13Service.selectListB13(wrapperB13);///获取科室信息
        if (b13List.size() == 0) {
            return fail("根据机构代码【" + inOrgId + "】科室名称【" + inDepartment + "】未查到科室信息。");
        }
        if (b13List.size() > 1) {
            return fail("根据机构代码【" + inOrgId + "】科室名称【" + inDepartment + "】查到多条科室信息。");
        }
        tran.setInKsdm(b13List.get(0).getKsDm());
        return new IReturn(true, tran);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 删除保存转出患者
     * @author caichunde
     * @date 2018-12-29
     */
    @RequestMapping(value = "/saveTranOutDel", method = RequestMethod.POST)
    @ResponseBody
    public IReturn saveTranOutDel(@RequestParam(value = "id") String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            return fail("参数为空。");
        }
        QueryWrapper<Tran> wrapper = new QueryWrapper();
        Tran tran = tranService.getOne(wrapper.eq("id", id));
        if (null == tran) {
            return fail("为查到患者信息。");
        }
        String state = tran.getTranState();
        if ("2".equals(state)) {
            return fail("该患者已转出生效，不允许删除。");
        }
        if ("5".equals(state)) {
            return fail("该患者已被接收，不允许删除。");
        }
        String report = tran.getSkerReport();
        if (!StringUtils.isEmpty(report)) {
            DocFileUtil.deleteFile(report);// 删除文件
            String path = DocFileUtil.getProjectPath();
            DocFileUtil.deleteEmptyFolder(path);// 清空空文件夹
        }
        return tranService.saveTranOutDel(id) == 1 ? success() : fail("操作失败。");
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取转出保存患者列表
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getSaveTranOutList", method = RequestMethod.POST)
    @ResponseBody
    public IReturn getSaveTranOutList(@RequestParam(value = "xm", required = false) String xm, HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        String id = String.valueOf(b1A.getId());

        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.eq("out_org_id", b1A.getOrgid());
        queryWrapper.eq("out_doctor_id", id);
        queryWrapper.in("nvl(tran_state, '0')", "0");
        if (!StringUtils.isEmpty(xm)) {
            queryWrapper.and(wrapper -> wrapper.like("sker_name", xm.trim())
                    .or().like("sker_idcardno", xm.trim()));
        }
        queryWrapper.orderByDesc("tran_date");
        List<Tran> tranList = tranService.selectList(queryWrapper);

        if (tranList.size() == 0) {
            return new IReturn(false, "无数据！");
        }
        return new IReturn(true, "操作成功", tranList);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取转出生效患者列表
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getValidTranOutList", method = RequestMethod.POST)
    @ResponseBody
    public IReturn getValidTranOutList(@RequestParam(value = "xm", required = false) String xm, String qssj, String zzsj, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        String id = String.valueOf(b1A.getId());

        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.eq("out_org_id", b1A.getOrgid());
        queryWrapper.eq("out_doctor_id", id);
        queryWrapper.in("tran_state", "2", "5", "6");
        if (StringUtils.isEmpty(qssj) && StringUtils.isEmpty(zzsj)) {
            String monthAgo = DateUtil.getMonthAgo(new Date());
            queryWrapper.ge("to_char(tran_date,'yyyymmddhh24miss')", monthAgo);
        } else {
            if (StringUtils.isEmpty(qssj)) {
                qssj = "19000101010101";
            }
            if (StringUtils.isEmpty(zzsj)) {
                zzsj = "30001231235959";
            }
            queryWrapper.between("to_char(tran_date,'yyyymmddhh24miss')", qssj, zzsj);
        }
        if (!StringUtils.isEmpty(xm)) {
            queryWrapper.and(wrapper -> wrapper.like("sker_name", xm.trim())
                    .or().like("sker_idcardno", xm.trim()));
        }
        queryWrapper.orderByDesc("tran_date");
        List<Tran> tranList = tranService.selectList(queryWrapper);
        if (tranList.size() == 0) {
            return new IReturn(false, "无数据！");
        }
        return new IReturn(true, "操作成功", tranList);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取未接收患者列表分页
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getNotReceiveTranListPage", method = RequestMethod.POST)
    @ResponseBody
    public IPage<Tran> getNotReceiveTranListPage(@RequestParam(value = "jgmcOrxm", required = false) String jgmcOrxm,
                                                 @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                 HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.in("tran_state", "2");
        queryWrapper.eq("in_org_id", b1A.getOrgid());
        if (!StringUtils.isEmpty(jgmcOrxm)) {
            queryWrapper.and(wrapper -> wrapper.like("out_org_name", jgmcOrxm.trim())
                    .or().like("sker_name", jgmcOrxm.trim())
                    .or().like("sker_idcardno", jgmcOrxm.trim()));
        }
        queryWrapper.orderByDesc("tran_date");
        return tranService.selectPage(pageNo, pageSize, queryWrapper);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 医生获取未接收患者列表分页
     * @author caichunde
     * @date 2019-1-9
     */
    @RequestMapping(value = "/getDoctorNotReceiveTranListPage", method = RequestMethod.POST)
    @ResponseBody
    public IReturn getDoctorNotReceiveTranListPage(@RequestParam(value = "xm", required = false) String xm,
                                                   @RequestParam(value = "department") String department,
                                                   @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (StringUtils.isEmpty(department)) {
            return fail("请选择科室！");
        }
        B1A b1A = (B1A) session.getAttribute("b1a");
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.in("tran_state", "2");
        queryWrapper.eq("in_org_id", b1A.getOrgid());
        queryWrapper.eq("in_department", department);
        if (!StringUtils.isEmpty(xm)) {
            queryWrapper.and(wrapper -> wrapper.like("out_org_name", xm.trim())
                    .or().like("sker_name", xm.trim())
                    .or().like("sker_idcardno", xm.trim()));
        }
        queryWrapper.orderByDesc("tran_date");
        return new IReturn(true, tranService.selectPage(pageNo, pageSize, queryWrapper));
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取未接收患者列表不分页
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getNotReceiveTranList", method = RequestMethod.POST)
    @ResponseBody
    public List<Tran> getNotReceiveTranList(@RequestParam(value = "jgmcOrxm", required = false) String jgmcOrxm,
                                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.in("tran_state", "2");
        queryWrapper.eq("in_org_id", b1A.getOrgid());
        if (!StringUtils.isEmpty(jgmcOrxm)) {
            queryWrapper.and(wrapper -> wrapper.like("out_org_name", jgmcOrxm.trim())
                    .or().like("sker_name", jgmcOrxm.trim())
                    .or().like("sker_idcardno", jgmcOrxm.trim()));
        }
        queryWrapper.orderByDesc("tran_date");
        return tranService.selectList(queryWrapper);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取已接收患者列表
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getReceiveTranListPage", method = RequestMethod.POST)
    @ResponseBody
    public IPage<Tran> getReceiveTranListPage(@RequestParam(value = "zrks", required = false) String zrks,
                                              @RequestParam(value = "xm", required = false) String xm,
                                              @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                              String qssj, String zzsj, HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.in("tran_state", "5");
        queryWrapper.eq("in_org_id", b1A.getOrgid());
        if (!StringUtils.isEmpty(zrks)) {
            queryWrapper.and(wrapper -> wrapper.like("in_department", zrks.trim()));
        }
        if (!StringUtils.isEmpty(xm)) {
            queryWrapper.and(wrapper -> wrapper.like("sker_name", xm.trim())
                    .or().like("sker_idcardno", xm.trim()));
        }
        if (StringUtils.isEmpty(qssj)) {
            qssj = "19000101010101";
        }
        if (StringUtils.isEmpty(zzsj)) {
            zzsj = "30001231235959";
        }
        queryWrapper.between("to_char(in_date,'yyyymmddhh24miss')", qssj, zzsj);
        queryWrapper.orderByDesc("in_date");
        return tranService.selectPage(pageNo, pageSize, queryWrapper);
    }

    /**
     * @param
     * @return ReturnMsgin_date
     * @Description: 获取拒收患者列表
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getRejectTranListPage", method = RequestMethod.POST)
    @ResponseBody
    public IPage<Tran> getRejectTranListPage(@RequestParam(value = "xm", required = false) String xm,
                                             @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                             String qssj, String zzsj, HttpServletRequest request) {
        HttpSession session = request.getSession();
        B1A b1A = (B1A) session.getAttribute("b1a");
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.in("tran_state", "6");
        queryWrapper.eq("in_org_id", b1A.getOrgid());
        if (!StringUtils.isEmpty(xm)) {
            queryWrapper.and(wrapper -> wrapper.like("sker_name", xm.trim())
                    .or().like("sker_idcardno", xm.trim()));
        }
        if (StringUtils.isEmpty(qssj)) {
            qssj = "19000101010101";
        }
        if (StringUtils.isEmpty(zzsj)) {
            zzsj = "30001231235959";
        }
        queryWrapper.between("to_char(in_date,'yyyymmddhh24miss')", qssj, zzsj);
        queryWrapper.orderByDesc("in_date");
        return tranService.selectPage(pageNo, pageSize, queryWrapper);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 修改患者转出状态
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/updateTranState", method = RequestMethod.POST)
    @ResponseBody
    public IReturn updateTranState(String id, String state) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(state)) {
            return new IReturn(false, "参数错误。");
        }
        Tran tran = new Tran();
        tran.setId(id);
        tran.setTranState(state);
        tran.setInDate(new Date());
        int count = tranService.updateTran(tran);
        if (count == 1) {
            return new IReturn(true, "操作成功！");
        }
        return new IReturn(false, "操作失败！");
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取机构科室信息
     * @author caichunde
     * @date 2018-12-29
     */
    @RequestMapping(value = "/getOrgDepartment", method = RequestMethod.POST)
    public IReturn getOrgDepartment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        QueryWrapper<B13> wrapper = new QueryWrapper();
        B1A b1A = (B1A) session.getAttribute("b1a");
        wrapper.eq("jg_dm", b1A.getOrgid());
        wrapper.eq("status", 1);
        wrapper.orderByAsc("ksmc");
        List<B13> departmentB13List = b13Service.selectListB13(wrapper);
        return new IReturn(true, departmentB13List);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 修改科室
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/updateTranDepartment", method = RequestMethod.POST)
    @ResponseBody
    public IReturn updateTranDepartment(String id, String department) {
        if (StringUtils.isEmpty(id) || StringUtils.isEmpty(department)) {
            return new IReturn(false, "参数错误。");
        }
        Tran tran = new Tran();
        tran.setId(id);
        tran.setInDepartment(department);
        int count = tranService.updateTran(tran);
        if (count == 1) {
            return new IReturn(true, "操作成功！");
        }
        return new IReturn(false, "操作失败！");
    }

    /**
     * @return ReturnMsg
     * @paramupdateTran
     * @Description: 修改医生意见
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/updateTranOpinion", method = RequestMethod.POST)
    @ResponseBody
    public IReturn updateTranOpinion(String id, String opinion) {
        if (StringUtils.isEmpty(id)) {
            return new IReturn(false, "参数错误。");
        }
        if (StringUtils.isEmpty(opinion)) {
            opinion = "无";
        }
        Tran tran = new Tran();
        tran.setId(id);
        tran.setDoctorOpinion(opinion);
        int count = tranService.updateTran(tran);
        if (count == 1) {
            return new IReturn(true, "操作成功！");
        }
        return new IReturn(false, "操作失败！");
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 下载上传附件文件信息
     * @author caichunde
     * @date 2018-12-29
     */
    @RequestMapping(value = "/downloadTranReport")
    public IReturn downloadTranReport(String id, HttpServletResponse response) throws Exception {
        QueryWrapper<Tran> wrapper = new QueryWrapper();
        Tran tran = tranService.getOne(wrapper.eq("id", id));
        String report = tran.getSkerReport();
        if (StringUtils.isEmpty(report)) {
            return fail("此患者没有上传附件。");
        }
        String wjmc = report.substring(report.lastIndexOf("\\") + 1, report.length());
        DocFileUtil.downloadFile(response, report, wjmc);
        return null;
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 导出患者列表
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/exportTranList")
    public IReturn exportTranList(@RequestParam(value = "zrks", required = false) String zrks,
                                  @RequestParam(value = "xm", required = false) String xm,
                                  String qssj, String zzsj, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();

        // 构建表头
        Map<String, String> map = new HashMap<>();
        map.put("id", "流水号");
        map.put("outOrgId", "转出机构Id");
        map.put("outOrgName", "转出机构名称");
        map.put("outDepartment", "转出科室");
        map.put("outDoctorId", "转出医生Id");

        map.put("outDoctorName", "转出医生姓名");
        map.put("outDoctorPhonenum", "转出医生电话");
        map.put("tranDirect", "转出方向");
        map.put("skerIdcardno", "患者身份证号");
        map.put("skerName", "患者姓名");

        map.put("skerSex", "患者性别");
        map.put("skerPhonenum", "患者电话");
        map.put("skerContent", "患者诊疗情况 下转：住院摘要，出院医嘱");
        map.put("skerFirstdiaCode", "患者初步诊断码");

        map.put("skerFirstdiaName", "患者初步诊");
        map.put("skerTranreasion", "转诊原因");
        map.put("inOrgId", "转入机构Id");
        map.put("inOrgName", "转入机构名称");

        map.put("inDepartment", "转入科室");
        map.put("tranDate", "转出日期");
        map.put("tranState", "转出状态");
        map.put("doctorOpinion", "医生意见");
        map.put("inDate", "转入日期");

        B1A b1A = (B1A) session.getAttribute("b1a");
        QueryWrapper<Tran> queryWrapper = new QueryWrapper();
        queryWrapper.select("id, out_org_id, out_org_name, out_department, out_doctor_id, out_doctor_name, out_doctor_phonenum, decode(tran_direct,'1','上','0','下','未知')tran_direct, sker_idcardno, sker_name, decode(sker_sex,'1','男','2','女','未知')sker_sex, sker_phonenum, sker_content, sker_firstdia_code, sker_firstdia_name, sker_report, sker_tranreasion, in_org_id, in_org_name, in_department, tran_date, decode(tran_state,'0','保存','2','转出生效','5','转入','6','拒收','未知')tran_state, doctor_opinion, in_date");
        queryWrapper.in("tran_state", "5");
        queryWrapper.eq("in_org_id", b1A.getOrgid());
        if (!StringUtils.isEmpty(zrks)) {
            queryWrapper.and(wrapper -> wrapper.like("in_department", zrks.trim()));
        }
        if (!StringUtils.isEmpty(xm)) {
            queryWrapper.and(wrapper -> wrapper.like("sker_name", xm.trim())
                    .or().like("sker_idcardno", xm.trim()));
        }
        if (StringUtils.isEmpty(qssj)) {
            qssj = "19000101010101";
        }
        if (StringUtils.isEmpty(zzsj)) {
            zzsj = "30001231235959";
        }
        queryWrapper.between("to_char(in_date,'yyyymmddhh24miss')", qssj, zzsj);
        queryWrapper.orderByAsc("id");
        List<Map<String, Object>> data = tranService.selectMaps(queryWrapper);
        if (data.size() == 0) {
            return fail("对不起，未查到数据。");
        }

        B00 b00 = agencyB00Service.getOne(new QueryWrapper<B00>().eq("orgid", b1A.getOrgid()));
        // 处理数据至Excel文件中
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(b00.getOrgName());
        Class c = new Tran().getClass();
        ExcelUtil.createExcel(workbook, sheet, c, data, map);

        //生成excel文件
        String fileName = b00.getOrgName() + ".xls";
        String filePath = DocFileUtil.getProjectPath() + "downloadFile" + File.separator;
        DocFileUtil.createPath(filePath);
        ExcelUtil.buildExcelFile(filePath, fileName, workbook);
        return new IReturn(true, filePath + fileName);
    }

    /**
     * @param String path 文件路径
     * @return ReturnMsg
     * @Description: 下载转入excel文件
     * @author caichunde
     * @date 2019-1-21
     */
    @RequestMapping(value = "/downloadFile")
    public IReturn downloadFile(String path, HttpServletResponse response) throws Exception {
        if (StringUtils.isEmpty(path)) {
            return fail("传入路径为空。");
        }
        String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        //下载excel文件
        DocFileUtil.downloadFile(response, path, fileName);
        // 删除文件
        DocFileUtil.deleteFile(path);
        return null;
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取转出患者诊断码信息
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getIcdtenList", method = RequestMethod.POST)
    public IReturn getIcdtenList(String iddName) {
        if (StringUtils.isEmpty(iddName)) {
            return fail("请输入查询内容。");
        }
        List<Icdten> icdtenIPage = icdtenService.getIcdtenList(iddName);
        if (icdtenIPage.size() == 0) {
            return fail("未查到诊断码信息。");
        }
        return new IReturn(true, "操作成功", icdtenIPage);
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 获取转出患者诊断码信息
     * @author caichunde
     * @date 2018-11-21
     */
    @RequestMapping(value = "/getIcdninev4List", method = RequestMethod.POST)
    public IReturn getIcdninev4List(String iddName) {
        if (StringUtils.isEmpty(iddName)) {
            return fail("请输入查询内容。");
        }
        List<Icdninev4> icdtenIPage = icdninev4Service.getIcdninev4List(iddName);
        if (icdtenIPage.size() == 0) {
            return fail("未查到诊断码信息。");
        }
        return new IReturn(true, "操作成功", icdtenIPage);
    }

}