package com.library.model;

public class MemberRecord {

    private Long memberId;
    private String type;
    private String dateOfMembership;
    private int noBooksIssued;
    private int maxBookLimit;
    private String name;
    private String address;
    private String phoneNo;

    public MemberRecord(Long memberId, String type, String dateOfMembership, int maxBookLimit, String name, String address, String phoneNo) {
        this.memberId = memberId;
        this.type = type;
        this.dateOfMembership = dateOfMembership;
        this.maxBookLimit = maxBookLimit;
        this.name = name;
        this.address = address;
        this.phoneNo = phoneNo;
        this.noBooksIssued = 0;
    }

    public MemberRecord getMember() {
        return this;
    }

    public void incBookIssued() {
        if (noBooksIssued < maxBookLimit) {
            noBooksIssued++;
        }
    }

    public void decBookIssued() {
        if (noBooksIssued > 0) {
            noBooksIssued--;
        }
    }

    public boolean canBorrow() {
        return noBooksIssued < maxBookLimit;
    }

    public void payBill(double amount) {
        System.out.println(name + " tarafından " + amount + " TL ödeme yapıldı.");
    }

    public Long getMemberId() {
        return memberId;
    }

    public int getNoBooksIssued() {
        return noBooksIssued;
    }

    public int getMaxBookLimit() {
        return maxBookLimit;
    }

    public String getName() {
        return name;
    }
}