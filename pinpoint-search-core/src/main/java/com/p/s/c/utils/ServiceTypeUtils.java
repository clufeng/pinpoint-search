package com.p.s.c.utils;

public class ServiceTypeUtils {

    public static String getServiceType(short serviceTypeCode) {
        return ServiceType.getServiceType(serviceTypeCode).name();
    }

    enum ServiceType {

        TOMCAT((short) 1010),
        SPRING_BOOT((short) 1210),
        JSF_PROVIDER((short)1900),
        JIMDB((short)8900),
        JMQ((short)8320),
        UN_KNOW((short)0);

        private short serviceTypeCode;

        ServiceType(short serviceTypeCode) {
            this.serviceTypeCode = serviceTypeCode;
        }

        static ServiceType getServiceType(short serviceTypeCode) {
            for (ServiceType serviceType : values()) {
                if(serviceType.serviceTypeCode == serviceTypeCode) {
                    return serviceType;
                }
            }
            return UN_KNOW;
        }

    }

}
