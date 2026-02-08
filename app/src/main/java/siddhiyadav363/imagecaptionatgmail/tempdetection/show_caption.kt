package siddhantbodake.imagecaptionatgmail.tempdetection

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.*
import androidx.databinding.DataBindingUtil
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
//import siddhantbodake.imagecaptionatgmail.tempdetection.databinding.ActivityMainBinding
import java.io.IOException
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


class show_caption : AppCompatActivity() {
    private lateinit var labelTextView: TextView
    private lateinit var valueTextView: TextView
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_caption)

        // Initialize views
        labelTextView = findViewById(R.id.labelTextView)
        valueTextView = findViewById(R.id.valueTextView)

        // Retrieve label and confidence values from intent extras
        val label = intent.getStringExtra("label")
        val confidence = intent.getFloatExtra("confidence", 0.0f)

        // Display label and confidence values in TextViews
        labelTextView.text = "Label: $label"
        valueTextView.text = "Confidence: $confidence"

        // Retrieve reference to Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().getReference("labels")

        // Query the database for the value associated with the label
        if (label != null) {
            databaseReference.child("Nature")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // If the label exists in the database, display its value in the TextView
                        if (dataSnapshot.exists()) {
                            val value = dataSnapshot.value.toString()
                            valueTextView.text = "Value: $value"
                        } else {
                            valueTextView.text = "Value not found"
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle any errors that occurred while querying the database
                        Toast.makeText(this@show_caption, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}






