/**
 * Copyright 2005-2012 Restlet S.A.S.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL
 * 1.0 (the "Licenses"). You can select the license that you prefer but you may
 * not use this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

package org.restlet.test.ext.odata.deepexpand.model;


import java.util.Date;
import java.util.List;

import org.restlet.test.ext.odata.deepexpand.model.Branch;
import org.restlet.test.ext.odata.deepexpand.model.CompanyPerson;
import org.restlet.test.ext.odata.deepexpand.model.Job;
import org.restlet.test.ext.odata.deepexpand.model.JobPartSpecialPayable;
import org.restlet.test.ext.odata.deepexpand.model.Location;
import org.restlet.test.ext.odata.deepexpand.model.Multilingual;
import org.restlet.test.ext.odata.deepexpand.model.Payment;
import org.restlet.test.ext.odata.deepexpand.model.Report;

/**
* Generated by the generator tool for the OData extension for the Restlet framework.<br>
*
* @see <a href="http://praktiki.metal.ntua.gr/CoopOData/CoopOData.svc/$metadata">Metadata of the target OData service</a>
*
*/
public class JobPart {

    private String comments;
    private Date endDate;
    private int id;
    private int paidDays;
    private String siteType;
    private Date startDate;
    private GeoLocation expeditionGeoLocation;
    private Tracking tracking;
    private Branch branch;
    private Multilingual description;
    private Location expeditionLocation;
    private Job job;
    private CompanyPerson managingCompanyPerson;
    private List<Payment> payments;
    private List<Report> reports;
    private List<JobPartSpecialPayable> specialPayables;

    /**
     * Constructor without parameter.
     * 
     */
    public JobPart() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param id
     *            The identifiant value of the entity.
     */
    public JobPart(int id) {
        this();
        this.id = id;
    }

   /**
    * Returns the value of the "comments" attribute.
    *
    * @return The value of the "comments" attribute.
    */
   public String getComments() {
      return comments;
   }
   /**
    * Returns the value of the "endDate" attribute.
    *
    * @return The value of the "endDate" attribute.
    */
   public Date getEndDate() {
      return endDate;
   }
   /**
    * Returns the value of the "id" attribute.
    *
    * @return The value of the "id" attribute.
    */
   public int getId() {
      return id;
   }
   /**
    * Returns the value of the "paidDays" attribute.
    *
    * @return The value of the "paidDays" attribute.
    */
   public int getPaidDays() {
      return paidDays;
   }
   /**
    * Returns the value of the "siteType" attribute.
    *
    * @return The value of the "siteType" attribute.
    */
   public String getSiteType() {
      return siteType;
   }
   /**
    * Returns the value of the "startDate" attribute.
    *
    * @return The value of the "startDate" attribute.
    */
   public Date getStartDate() {
      return startDate;
   }
   /**
    * Returns the value of the "expeditionGeoLocation" attribute.
    *
    * @return The value of the "expeditionGeoLocation" attribute.
    */
   public GeoLocation getExpeditionGeoLocation() {
      return expeditionGeoLocation;
   }
   /**
    * Returns the value of the "tracking" attribute.
    *
    * @return The value of the "tracking" attribute.
    */
   public Tracking getTracking() {
      return tracking;
   }
   /**
    * Returns the value of the "branch" attribute.
    *
    * @return The value of the "branch" attribute.
    */
   public Branch getBranch() {
      return branch;
   }
   
   /**
    * Returns the value of the "description" attribute.
    *
    * @return The value of the "description" attribute.
    */
   public Multilingual getDescription() {
      return description;
   }
   
   /**
    * Returns the value of the "expeditionLocation" attribute.
    *
    * @return The value of the "expeditionLocation" attribute.
    */
   public Location getExpeditionLocation() {
      return expeditionLocation;
   }
   
   /**
    * Returns the value of the "job" attribute.
    *
    * @return The value of the "job" attribute.
    */
   public Job getJob() {
      return job;
   }
   
   /**
    * Returns the value of the "managingCompanyPerson" attribute.
    *
    * @return The value of the "managingCompanyPerson" attribute.
    */
   public CompanyPerson getManagingCompanyPerson() {
      return managingCompanyPerson;
   }
   
   /**
    * Returns the value of the "payments" attribute.
    *
    * @return The value of the "payments" attribute.
    */
   public List<Payment> getPayments() {
      return payments;
   }
   
   /**
    * Returns the value of the "reports" attribute.
    *
    * @return The value of the "reports" attribute.
    */
   public List<Report> getReports() {
      return reports;
   }
   
   /**
    * Returns the value of the "specialPayables" attribute.
    *
    * @return The value of the "specialPayables" attribute.
    */
   public List<JobPartSpecialPayable> getSpecialPayables() {
      return specialPayables;
   }
   
   /**
    * Sets the value of the "comments" attribute.
    *
    * @param comments
    *     The value of the "comments" attribute.
    */
   public void setComments(String comments) {
      this.comments = comments;
   }
   /**
    * Sets the value of the "endDate" attribute.
    *
    * @param endDate
    *     The value of the "endDate" attribute.
    */
   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }
   /**
    * Sets the value of the "id" attribute.
    *
    * @param id
    *     The value of the "id" attribute.
    */
   public void setId(int id) {
      this.id = id;
   }
   /**
    * Sets the value of the "paidDays" attribute.
    *
    * @param paidDays
    *     The value of the "paidDays" attribute.
    */
   public void setPaidDays(int paidDays) {
      this.paidDays = paidDays;
   }
   /**
    * Sets the value of the "siteType" attribute.
    *
    * @param siteType
    *     The value of the "siteType" attribute.
    */
   public void setSiteType(String siteType) {
      this.siteType = siteType;
   }
   /**
    * Sets the value of the "startDate" attribute.
    *
    * @param startDate
    *     The value of the "startDate" attribute.
    */
   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }
   /**
    * Sets the value of the "expeditionGeoLocation" attribute.
    *
    * @param expeditionGeoLocation
    *     The value of the "expeditionGeoLocation" attribute.
    */
   public void setExpeditionGeoLocation(GeoLocation expeditionGeoLocation) {
      this.expeditionGeoLocation = expeditionGeoLocation;
   }
   
   /**
    * Sets the value of the "tracking" attribute.
    *
    * @param tracking
    *     The value of the "tracking" attribute.
    */
   public void setTracking(Tracking tracking) {
      this.tracking = tracking;
   }
   
   /**
    * Sets the value of the "branch" attribute.
    *
    * @param branch"
    *     The value of the "branch" attribute.
    */
   public void setBranch(Branch branch) {
      this.branch = branch;
   }

   /**
    * Sets the value of the "description" attribute.
    *
    * @param description"
    *     The value of the "description" attribute.
    */
   public void setDescription(Multilingual description) {
      this.description = description;
   }

   /**
    * Sets the value of the "expeditionLocation" attribute.
    *
    * @param expeditionLocation"
    *     The value of the "expeditionLocation" attribute.
    */
   public void setExpeditionLocation(Location expeditionLocation) {
      this.expeditionLocation = expeditionLocation;
   }

   /**
    * Sets the value of the "job" attribute.
    *
    * @param job"
    *     The value of the "job" attribute.
    */
   public void setJob(Job job) {
      this.job = job;
   }

   /**
    * Sets the value of the "managingCompanyPerson" attribute.
    *
    * @param managingCompanyPerson"
    *     The value of the "managingCompanyPerson" attribute.
    */
   public void setManagingCompanyPerson(CompanyPerson managingCompanyPerson) {
      this.managingCompanyPerson = managingCompanyPerson;
   }

   /**
    * Sets the value of the "payments" attribute.
    *
    * @param payments"
    *     The value of the "payments" attribute.
    */
   public void setPayments(List<Payment> payments) {
      this.payments = payments;
   }

   /**
    * Sets the value of the "reports" attribute.
    *
    * @param reports"
    *     The value of the "reports" attribute.
    */
   public void setReports(List<Report> reports) {
      this.reports = reports;
   }

   /**
    * Sets the value of the "specialPayables" attribute.
    *
    * @param specialPayables"
    *     The value of the "specialPayables" attribute.
    */
   public void setSpecialPayables(List<JobPartSpecialPayable> specialPayables) {
      this.specialPayables = specialPayables;
   }

}