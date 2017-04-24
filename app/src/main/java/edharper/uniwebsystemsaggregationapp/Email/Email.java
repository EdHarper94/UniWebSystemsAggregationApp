package edharper.uniwebsystemsaggregationapp.Email;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @file Email.java
 * @author Ed Harper
 * @date 28/03/2017
 *
 * Email object class for holding and maintaing Email data
 * Implements Parcelable to allow for parsing object via intents
 */

public class Email implements Parcelable {

    private Long UID;
    private String subject;
    private String message;
    private Boolean attachment;

    public Email(){

    }

    /**
     * Initialises email object for storing and maintaining email data.
     * @param UID
     * @param subject
     * @param message
     * @param attachment
     */
    public Email(Long UID, String subject, String message, Boolean attachment){
        this.UID = UID;
        this.subject = subject;
        this.message = message;
        this.attachment = attachment;
    }

    public long getUID(){
        return UID;
    }

    public String getSubject(){
        return subject;
    }

    public String getMessage(){
        return message;
    }

    public Boolean getAttachment(){
        return attachment;
    }

    public String toString(){
        return "ID: " + getUID() + ". Subject: " + getSubject() + ". Message: " + getMessage() + ". Attachment: " + getAttachment();
    }

    /**
     * Initialises parcel with data passed from in
     * @param in
     */
    protected Email(Parcel in) {
        UID = in.readByte() == 0x00 ? null : in.readLong();
        subject = in.readString();
        message = in.readString();
        byte attachmentVal = in.readByte();
        attachment = attachmentVal == 0x02 ? null : attachmentVal != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Packages object into parcel
     * @param dest the destination parcel
     * @param flags flags about how the object should be written
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (UID == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeLong(UID);
        }
        dest.writeString(subject);
        dest.writeString(message);
        if (attachment == null) {
            dest.writeByte((byte) (0x02));
        } else {
            dest.writeByte((byte) (attachment ? 0x01 : 0x00));
        }
    }

    @SuppressWarnings("unused")
    public static final Creator<Email> CREATOR = new Creator<Email>() {
        @Override
        public Email createFromParcel(Parcel in) {
            return new Email(in);
        }

        @Override
        public Email[] newArray(int size) {
            return new Email[size];
        }
    };
}
