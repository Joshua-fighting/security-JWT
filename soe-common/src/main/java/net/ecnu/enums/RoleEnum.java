package net.ecnu.enums;


import java.util.Arrays;
import java.util.Optional;

public enum RoleEnum {

    SUPER_ADMINISTRATOR(1,"超级管理员"),
    ADMINISTRATOR(2,"管理员"),
    TrainerA(3,"A级培训师"),
    TrainerB(4,"B级培训师"),
    TrainerC(5,"C级培训师"),
    STUDENT(6,"学生")
    ;

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    RoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getNameByCode(Integer roleId){
        Optional<RoleEnum> first = Arrays.stream(RoleEnum.values()).filter(roleEnum -> {
            if (roleEnum.getCode() == roleId) {
                return true;
            } else {
                return false;
            }
        }).findFirst();
        return first.get().getName();
    }
}
