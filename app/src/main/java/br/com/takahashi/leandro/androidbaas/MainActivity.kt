package br.com.takahashi.leandro.androidbaas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.*
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("temperatura")

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Long::class.java).toString()
                tvTemperatura.text = value

                    FirebaseMessaging.getInstance().subscribeToTopic("minhatemperaturaiot")
                
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
}
