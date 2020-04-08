package com.p.s.pinpoint.utils;

public class ServiceTypeUtils {

    public static String getServiceType(short serviceTypeCode) {
        return ServiceType.getServiceType(serviceTypeCode).name();
    }

    enum ServiceType {

        TOMCAT((short) 1010),
        SPRING_BOOT((short) 1210),
        STAND_ALONE((short) 1000),
        DUBBO_PROVIDER((short) 1110),
        UN_KNOW((short) 0);

        private short serviceTypeCode;

        ServiceType(short serviceTypeCode) {
            this.serviceTypeCode = serviceTypeCode;
        }

        static ServiceType getServiceType(short serviceTypeCode) {
            for (ServiceType serviceType : values()) {
                if (serviceType.serviceTypeCode == serviceTypeCode) {
                    return serviceType;
                }
            }
            return UN_KNOW;
        }

    }

}
