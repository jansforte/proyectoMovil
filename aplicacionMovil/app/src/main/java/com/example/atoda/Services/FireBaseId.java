package com.example.atoda.Services;

import androidx.annotation.NonNull;
import com.google.firebase.messaging.FirebaseMessagingService;

/**
 * Created by KevinPiazzoli on 09/02/2017.
 */

public class FireBaseId extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
