package com.marcelmika.lims.persistence.domain;

import com.marcelmika.lims.api.entity.BuddyDetails;
import com.marcelmika.lims.persistence.generated.model.Participant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ing. Marcel Mika
 * @link http://marcelmika.com
 * Date: 2/4/14
 * Time: 11:55 PM
 */
public class Buddy {

    private Long buddyId;
    private Long portraitId;
    private String fullName;
    private String screenName;
    private String password;
    private String status;

    /**
     * Create new user and maps data from user details
     *
     * @param buddyDetails BuddyDetails
     * @return User
     */
    public static Buddy fromBuddyDetails(BuddyDetails buddyDetails) {
        // Create new buddy
        Buddy buddy = new Buddy();
        // Map data to user details
        buddy.setBuddyId(buddyDetails.getBuddyId());
        buddy.setFullName(buddyDetails.getFullName());
        buddy.setPortraitId(buddyDetails.getPortraitId());
        buddy.setScreenName(buddyDetails.getScreenName());
        buddy.setPassword(buddyDetails.getPassword());
        buddy.setStatus(buddyDetails.getStatus());

        return buddy;
    }


    /**
     * Factory method which creates new list of Buddies from the list of BuddyDetails
     *
     * @param detailsList list of buddy details
     * @return List<Buddy> of buddies
     */
    public static List<Buddy> fromBuddyDetailsList(List<BuddyDetails> detailsList) {
        // Create new list of buddies
        List<Buddy> buddies = new ArrayList<Buddy>();

        // Iterate through details and create buddy based on that
        for (BuddyDetails details : detailsList) {
            buddies.add(Buddy.fromBuddyDetails(details));
        }

        return buddies;
    }

    /**
     * Factory method which creates buddy from participant model
     *
     * @param participant Participant
     * @return Buddy
     */
    public static Buddy fromParticipantModel(Participant participant) {
        // Create new buddy
        Buddy buddy = new Buddy();
        // Map data from model
        buddy.setBuddyId(participant.getParticipantId());

        return buddy;
    }

    /**
     * Factory method which creates buddy from plain java object usually retrieved from database
     *
     * @param object Object
     * @return Buddy
     */
    public static Buddy fromPlainObject(Object[] object) {
        // Create new buddy
        Buddy buddy = new Buddy();
        // Map data from object
        buddy.buddyId = (Long)object[0];
        buddy.screenName = (String)object[1];
        buddy.fullName = String.format("%s %s %s", object[2], object[3], object[4]);
        buddy.status = String.format("%s", object[5]);

        return buddy;
    }

    /**
     * Maps user to user details
     *
     * @return UserDetails
     */
    public BuddyDetails toBuddyDetails() {
        // Create new user details
        BuddyDetails details = new BuddyDetails();
        // Map data from user
        details.setBuddyId(buddyId);
        details.setFullName(fullName);
        details.setPortraitId(portraitId);
        details.setScreenName(screenName);
        details.setPassword(password);
        details.setStatus(status);

        return details;
    }

    public Long getBuddyId() {
        return buddyId;
    }

    public void setBuddyId(Long buddyId) {
        this.buddyId = buddyId;
    }

    public Long getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(Long portraitId) {
        this.portraitId = portraitId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
