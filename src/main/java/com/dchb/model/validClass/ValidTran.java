package com.dchb.model.validClass;

import org.springframework.web.multipart.MultipartFile;

public class ValidTran {
    //转出机构id
    private String id;
    //转出机构id
    private String mechaniid;
    //转出机构名称
    private String mechanism;
    //转入科室名称
    private String department;
    //医生id
    private String doctorId;
    //医生姓名
    private String doctorName;
    //转入机构id
    private String mechanismIntoId;
    //转入机构名称
    private String mechanismInto;
    //转入科室名称
    private String departmentInto;
    //医生电话
    private String doctorPhone;
    //转出方向
    private String resource;
    //患者身份证号
    private String patientIDcard;
    //患者姓名
    private String patientName;
    //患者性别
    private String patientSex;
    //患者电话
    private String patientPhone;
    //患者诊疗情况
    private String patientTreatmentSituation;
    //患者诊断码
    private String patientDiagnosticCode;
    //患者初步判断
    private String patientPreliminaryJudgment;
    //患者诊疗附件
    private String patientAttachment;

    public MultipartFile[] getFiles() {
        return files;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    //转诊原因
    private String tranReason;
    //记录表单状态，0：保存；2：转出生效；
    private String state;

    private MultipartFile[] files;

    public String getMechanism() {
        return mechanism;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getPatientIDcard() {
        return patientIDcard;
    }

    public void setPatientIDcard(String patientIDcard) {
        this.patientIDcard = patientIDcard;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSex() {
        return patientSex;
    }

    public void setPatientSex(String patientSex) {
        this.patientSex = patientSex;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getPatientTreatmentSituation() {
        return patientTreatmentSituation;
    }

    public void setPatientTreatmentSituation(String patientTreatmentSituation) {
        this.patientTreatmentSituation = patientTreatmentSituation;
    }

    public String getPatientDiagnosticCode() {
        return patientDiagnosticCode;
    }

    public void setPatientDiagnosticCode(String patientDiagnosticCode) {
        this.patientDiagnosticCode = patientDiagnosticCode;
    }

    public String getPatientPreliminaryJudgment() {
        return patientPreliminaryJudgment;
    }

    public void setPatientPreliminaryJudgment(String patientPreliminaryJudgment) {
        this.patientPreliminaryJudgment = patientPreliminaryJudgment;
    }

    public String getPatientAttachment() {
        return patientAttachment;
    }

    public void setPatientAttachment(String patientAttachment) {
        this.patientAttachment = patientAttachment;
    }

    public String getTranReason() {
        return tranReason;
    }

    public void setTranReason(String tranReason) {
        this.tranReason = tranReason;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMechaniid() {
        return mechaniid;
    }

    public void setMechaniid(String mechaniid) {
        this.mechaniid = mechaniid;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getMechanismIntoId() {
        return mechanismIntoId;
    }

    public void setMechanismIntoId(String mechanismIntoId) {
        this.mechanismIntoId = mechanismIntoId;
    }

    public String getMechanismInto() {
        return mechanismInto;
    }

    public void setMechanismInto(String mechanismInto) {
        this.mechanismInto = mechanismInto;
    }

    public String getDepartmentInto() {
        return departmentInto;
    }

    public void setDepartmentInto(String departmentInto) {
        this.departmentInto = departmentInto;
    }

}
