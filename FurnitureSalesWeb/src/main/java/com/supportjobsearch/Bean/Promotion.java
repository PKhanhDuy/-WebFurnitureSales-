package com.supportjobsearch.Bean;

import com.supportjobsearch.enums.DiscountType;
import com.supportjobsearch.enums.PromotionStatus;

import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;

public class Promotion implements Serializable {
    private int id;
    private String codes;
    private DiscountType type;
    private int limitPro;
    private double valueOfPro;
    private PromotionStatus statusOfPro;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String fullValueOfPro;

    public Promotion() {
    }

    public Promotion(int id, String codes, DiscountType type, int limitePro, double valueOfPro, PromotionStatus statusOfPro, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.codes = codes;
        this.type = type;
        this.limitPro = limitePro;
        this.valueOfPro = valueOfPro;
        this.statusOfPro = statusOfPro;
        this.startDate = startDate;
        this.endDate = endDate;

        NumberFormat formatter = NumberFormat.getInstance(Locale.ENGLISH);
        fullValueOfPro = formatter.format(valueOfPro);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public DiscountType getType() {
        return type;
    }

    public void setType(DiscountType type) {
        this.type = type;
    }

    public int getLimitPro() {
        return limitPro;
    }

    public void setLimitPro(int limitPro) {
        this.limitPro = limitPro;
    }

    public double getValueOfPro() {
        return valueOfPro;
    }

    public void setValueOfPro(double valueOfPro) {
        this.valueOfPro = valueOfPro;
    }

    public PromotionStatus getStatusOfPro() {
        return statusOfPro;
    }

    public void setStatusOfPro(PromotionStatus statusOfPro) {
        this.statusOfPro = statusOfPro;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getFullValueOfPro() {
        return fullValueOfPro;
    }

    public void setFullValueOfPro(String fullValueOfPro) {
        this.fullValueOfPro = fullValueOfPro;
    }

    @Override
    public String toString() {
        return "Promotion {id =" + id +
               ", code = " + codes +
               ", type = " + type + ", " +
               "limit = " + fullValueOfPro +
               ", value = " + valueOfPro +
               ", status = " + statusOfPro +
               ", startDate = " + startDate +
               ", endDate = " + endDate +
               '}';
    }
}
