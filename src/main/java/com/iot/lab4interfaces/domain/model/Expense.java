package com.iot.lab4interfaces.domain.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"budget_fiscal_year",
    "ftyp",
    "fundtype",
    "appr",
    "appropriation",
    "service_number",
    "service",
    "objectgroup",
    "expenditure_classification",
    "current_budget",
    "encumbrance",
    "expenses",
    "encumb_expense"})
@Generated("jsonschema2pojo")
public class Expense implements Serializable {

  private final static long serialVersionUID = 5002435807022453247L;
  @JsonProperty("budget_fiscal_year") private String budgetFiscalYear;
  @JsonProperty("ftyp") private String ftyp;
  @JsonProperty("fundtype") private String fundtype;
  @JsonProperty("appr") private String appr;
  @JsonProperty("appropriation") private String appropriation;
  @JsonProperty("service_number") private String serviceNumber;
  @JsonProperty("service") private String service;
  @JsonProperty("objectgroup") private String objectgroup;
  @JsonProperty("expenditure_classification") private String expenditureClassification;
  @JsonProperty("current_budget") private String currentBudget;
  @JsonProperty("encumbrance") private String encumbrance;
  @JsonProperty("expenses") private String expenses;
  @JsonProperty("encumb_expense") private String encumbExpense;
  @JsonIgnore private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("budget_fiscal_year")
  public String getBudgetFiscalYear() {
    return budgetFiscalYear;
  }

  @JsonProperty("budget_fiscal_year")
  public void setBudgetFiscalYear(String budgetFiscalYear) {
    this.budgetFiscalYear = budgetFiscalYear;
  }

  @JsonProperty("ftyp")
  public String getFtyp() {
    return ftyp;
  }

  @JsonProperty("ftyp")
  public void setFtyp(String ftyp) {
    this.ftyp = ftyp;
  }

  @JsonProperty("fundtype")
  public String getFundtype() {
    return fundtype;
  }

  @JsonProperty("fundtype")
  public void setFundtype(String fundtype) {
    this.fundtype = fundtype;
  }

  @JsonProperty("appr")
  public String getAppr() {
    return appr;
  }

  @JsonProperty("appr")
  public void setAppr(String appr) {
    this.appr = appr;
  }

  @JsonProperty("appropriation")
  public String getAppropriation() {
    return appropriation;
  }

  @JsonProperty("appropriation")
  public void setAppropriation(String appropriation) {
    this.appropriation = appropriation;
  }

  @JsonProperty("service_number")
  public String getServiceNumber() {
    return serviceNumber;
  }

  @JsonProperty("service_number")
  public void setServiceNumber(String serviceNumber) {
    this.serviceNumber = serviceNumber;
  }

  @JsonProperty("service")
  public String getService() {
    return service;
  }

  @JsonProperty("service")
  public void setService(String service) {
    this.service = service;
  }

  @JsonProperty("objectgroup")
  public String getObjectgroup() {
    return objectgroup;
  }

  @JsonProperty("objectgroup")
  public void setObjectgroup(String objectgroup) {
    this.objectgroup = objectgroup;
  }

  @JsonProperty("expenditure_classification")
  public String getExpenditureClassification() {
    return expenditureClassification;
  }

  @JsonProperty("expenditure_classification")
  public void setExpenditureClassification(String expenditureClassification) {
    this.expenditureClassification = expenditureClassification;
  }

  @JsonProperty("current_budget")
  public String getCurrentBudget() {
    return currentBudget;
  }

  @JsonProperty("current_budget")
  public void setCurrentBudget(String currentBudget) {
    this.currentBudget = currentBudget;
  }

  @JsonProperty("encumbrance")
  public String getEncumbrance() {
    return encumbrance;
  }

  @JsonProperty("encumbrance")
  public void setEncumbrance(String encumbrance) {
    this.encumbrance = encumbrance;
  }

  @JsonProperty("expenses")
  public String getExpenses() {
    return expenses;
  }

  @JsonProperty("expenses")
  public void setExpenses(String expenses) {
    this.expenses = expenses;
  }

  @JsonProperty("encumb_expense")
  public String getEncumbExpense() {
    return encumbExpense;
  }

  @JsonProperty("encumb_expense")
  public void setEncumbExpense(String encumbExpense) {
    this.encumbExpense = encumbExpense;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(Expense.class.getName())
        .append('@')
        .append(Integer.toHexString(System.identityHashCode(this)))
        .append('[');
    sb.append("budgetFiscalYear");
    sb.append('=');
    sb.append(((this.budgetFiscalYear == null) ? "<null>" : this.budgetFiscalYear));
    sb.append(',');
    sb.append("ftyp");
    sb.append('=');
    sb.append(((this.ftyp == null) ? "<null>" : this.ftyp));
    sb.append(',');
    sb.append("fundtype");
    sb.append('=');
    sb.append(((this.fundtype == null) ? "<null>" : this.fundtype));
    sb.append(',');
    sb.append("appr");
    sb.append('=');
    sb.append(((this.appr == null) ? "<null>" : this.appr));
    sb.append(',');
    sb.append("appropriation");
    sb.append('=');
    sb.append(((this.appropriation == null) ? "<null>" : this.appropriation));
    sb.append(',');
    sb.append("serviceNumber");
    sb.append('=');
    sb.append(((this.serviceNumber == null) ? "<null>" : this.serviceNumber));
    sb.append(',');
    sb.append("service");
    sb.append('=');
    sb.append(((this.service == null) ? "<null>" : this.service));
    sb.append(',');
    sb.append("objectgroup");
    sb.append('=');
    sb.append(((this.objectgroup == null) ? "<null>" : this.objectgroup));
    sb.append(',');
    sb.append("expenditureClassification");
    sb.append('=');
    sb.append(((this.expenditureClassification == null) ? "<null>" : this.expenditureClassification));
    sb.append(',');
    sb.append("currentBudget");
    sb.append('=');
    sb.append(((this.currentBudget == null) ? "<null>" : this.currentBudget));
    sb.append(',');
    sb.append("encumbrance");
    sb.append('=');
    sb.append(((this.encumbrance == null) ? "<null>" : this.encumbrance));
    sb.append(',');
    sb.append("expenses");
    sb.append('=');
    sb.append(((this.expenses == null) ? "<null>" : this.expenses));
    sb.append(',');
    sb.append("encumbExpense");
    sb.append('=');
    sb.append(((this.encumbExpense == null) ? "<null>" : this.encumbExpense));
    sb.append(',');
    sb.append("additionalProperties");
    sb.append('=');
    sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
    sb.append(',');
    if (sb.charAt((sb.length() - 1)) == ',') {
      sb.setCharAt((sb.length() - 1), ']');
    } else {
      sb.append(']');
    }
    return sb.toString();
  }

}