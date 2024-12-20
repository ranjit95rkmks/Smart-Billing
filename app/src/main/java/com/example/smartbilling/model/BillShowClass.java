package com.example.smartbilling.model;

public class BillShowClass {

//    record['id'] = row[0];
//    record['book'] =row[1];
//    record['account'] = row[2];
//    record['bill_month'] = row[3];
//    record['bill_year'] = row[4];
//    record['c_reading_unit'] = row[5];
//    record['pre_reading_unit'] = row[6];
//    record['dif_unit'] = row[7];
//    record['tarrif'] = row[8];
//    record['demand'] = row[10];
//    record['posting_date'] = row[11];
//    record['issue_date'] = row[12];
//    record['lpc_date'] = row[13];
//    record['discon_date'] = row[14];
//    record['nb'] = row[15];
//    record['vat'] = row[16];
//    record['lpc'] = row[17];
//    record['demand_charge'] = row[18];
//    record['total_amount'] = row[19];
//    record['other_amount'] = row[20];
//    record['gtotal_taka'] = row[21];
//    record['user'] = row[22];
//    record['bill_type'] =row[23];
//    record['bill_number'] = row[24];
//    record['sms_number'] = row[25];

    String id, bookno, accono, billmonth, billyear, c_reading, nb, demand_charge, gTotal_taka, bill_type, status;


    public BillShowClass(String id, String bookno, String accono, String billmonth, String billyear, String c_reading, String nb, String demand_charge, String gTotal_taka, String bill_type, String status){
        this.id = id;
        this.bill_type = bill_type;
        this.c_reading = c_reading;
        this.bookno = bookno;
        this.billmonth = billmonth;
        this.billyear = billyear;
        this.accono =accono;
        this.demand_charge = demand_charge;
        this.gTotal_taka = gTotal_taka;
        this.nb = nb;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getBookno() {
        return bookno;
    }

    public String getAccono() {
        return accono;
    }

    public String getBillmonth() {
        return billmonth;
    }

    public String getBillyear() {
        return billyear;
    }

    public String getC_reading() {
        return c_reading;
    }

    public String getNb() {
        return nb;
    }

    public String getDemand_charge() {
        return demand_charge;
    }

    public String getgTotal_taka() {
        return gTotal_taka;
    }

    public String getBill_type() {
        return bill_type;
    }

    public String getStatus() {
        return status;
    }
}
