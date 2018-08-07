package com.clicks.kv.unseenpics;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Region;

public class AmazonClient {
    private AmazonS3Client s3Client;
    private TransferUtility transferUtility;
    private static AmazonClient sManager;

    private AmazonClient() {
        AWSCredentials credentials = new BasicAWSCredentials(Constants.AWS_ACCESS_KEY, Constants.AWS_SECRET_KEY);
        s3Client = new AmazonS3Client(credentials);
        s3Client.setRegion(Region.US_East_2.toAWSRegion());
        transferUtility = TransferUtility.builder().s3Client(s3Client).context(TheApplication.mInstance).build();
    }

    static AmazonClient getManager() {
        if (sManager == null) {
            synchronized (AmazonClient.class) {
                if (sManager == null) {
                    sManager = new AmazonClient();
                }
            }
        }
        return sManager;
    }

    public static AmazonClient getClient() {
        return AmazonClient.getManager();
    }

    public TransferUtility getTransferUtility() {
        return transferUtility;
    }

    public AmazonS3Client getS3Client() { return s3Client;}
}