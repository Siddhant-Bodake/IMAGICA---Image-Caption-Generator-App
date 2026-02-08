package siddhantbodake.imagecaptionatgmail.tempdetection

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeler
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
//import siddhantbodake.imagecaptionatgmail.tempdetection.R.id.textview

class imageUpload : AppCompatActivity() {

    lateinit var imageView: ImageView
    lateinit var uploadButton: Button
    lateinit var labelButton: Button
    lateinit var textView: TextView
    lateinit var filePath: Uri
    lateinit var bitmap: Bitmap
    private val PICK_IMAGE_REQUEST = 1234
    lateinit var storage: FirebaseStorage
    lateinit var labeler: ImageLabeler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upload_image)

        imageView = findViewById(R.id.imageView)
        uploadButton = findViewById(R.id.btn_upload)
        labelButton = findViewById(R.id.btn_next)
        textView = findViewById(R.id.textview)
        storage = FirebaseStorage.getInstance()
        labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        uploadButton.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST)
        }

        labelButton.setOnClickListener {
            val inputImage = InputImage.fromBitmap(bitmap, 0)
            labeler.process(inputImage)
                .addOnSuccessListener { labels ->
                    for (label in labels) {
                        val labelText = label.text
                        val confidence = label.confidence
                        textView.append("$labelText : $confidence\n")
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("Image Labeling Error", "Error occurred while labeling the image", e)
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            filePath = data.data!!
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imageView.setImageBitmap(bitmap)
                uploadImage()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        val ref = storage.reference.child("images/" + System.currentTimeMillis() + ".jpg")
        val uploadTask: UploadTask = ref.putFile(filePath)
        uploadTask.addOnSuccessListener {
            Log.e("Firebase Storage", "Image uploaded successfully")
        }.addOnFailureListener {
            Log.e("Firebase Storage", "Error occurred while uploading the image")
        }
    }
}
