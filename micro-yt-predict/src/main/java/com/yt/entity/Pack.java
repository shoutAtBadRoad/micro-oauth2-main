package com.yt.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Pack {
    private String orderTypeCode;
    private String wayBillNo;
    private String wayBillStatus;
    private Date orderCreateTime;
    private Date canvassTime;
    private String canvassOrgCode;
    private String deliveryOrgCode;
}
