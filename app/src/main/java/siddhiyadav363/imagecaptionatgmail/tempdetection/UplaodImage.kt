package siddhantbodake.imagecaptionatgmail.tempdetection
/*
import android.app.Instrumentation.ActivityResult
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.StorageReference
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage

import java.lang.ref.PhantomReference

class UplaodImage : AppCompatActivity() {
    private lateinit var btnUpload: Button
    private lateinit var btnNext: Button
    private lateinit var imageView: ImageView
    private lateinit var storageReference: StorageReference
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upload_image)

        storageReference = Firebase.storage.reference
        btnUpload = findViewById(R.id.btn_upload)
        imageView = findViewById(R.id.imageView)
        btnNext = findViewById(R.id.btn_next)

        val galleryImage = registerForActivityResult(
            ActivityResultContracts.GetContent(),
            ActivityResultCallback {
                imageView.setImageURI(it)
                if (it != null) {
                    uri = it
                }
            }
        )

        btnUpload.setOnClickListener {
            galleryImage.launch("image/*")
        }

        btnNext.setOnClickListener {
            if (!::uri.isInitialized) {
                Toast.makeText(this, "Please select an image to upload", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Retrieve a reference to the image in Firebase Storage
            val storageReference = FirebaseStorage.getInstance().getReference("Images")
            val imageRef = storageReference.child("${System.currentTimeMillis()}.jpeg")

            // Upload the image to Firebase Storage
            imageRef.putFile(uri)
                .addOnSuccessListener { task ->
                    // Retrieve the download URL of the image
                    task.storage.downloadUrl
                        .addOnSuccessListener { uri ->
                            // Create a FirebaseVisionImage object from the image URL
                            val visionImage = FirebaseVisionImage.fromFilePath(this, uri)

                            // Get a FirebaseVisionImageLabeler instance
                            val labeler = FirebaseVision.getInstance().onDeviceImageLabeler

                            // Use the labeler to detect labels in the image
                            labeler.processImage(visionImage)
                                .addOnSuccessListener { labels ->
                                    // Extract the label and confidence values from the labels list
                                    val label = labels[0].text
                                    val confidence = labels[0].confidence

                                    // Start the target activity and pass the label and confidence values as extras
                                    val intent = Intent(this, show_caption::class.java)
                                    val bundle = Bundle()
                                    bundle.putString("label", label)
                                    bundle.putFloat("confidence", confidence)
                                    intent.putExtras(bundle)
                                    startActivity(intent)

                                }
                                .addOnFailureListener { e ->
                                    // Handle any errors that occurred during labeling
                                    Toast.makeText(this, "Labeling failed: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        }
                        .addOnFailureListener { e ->
                            // Handle any errors that occurred while retrieving the download URL
                            Toast.makeText(this, "Retrieving download URL failed: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener { e ->
                    // Handle any errors that occurred during the upload
                    Toast.makeText(this, "Upload failed: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}


*/