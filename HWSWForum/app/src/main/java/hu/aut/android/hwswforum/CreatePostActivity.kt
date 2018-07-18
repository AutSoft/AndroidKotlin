package hu.aut.android.hwswforum

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import hu.aut.android.hwswforum.data.Post
import kotlinx.android.synthetic.main.activity_create_post.*
import java.io.ByteArrayOutputStream
import java.net.URLEncoder
import java.util.*

class CreatePostActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 101
        private const val CAMERA_REQUEST_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        requestNeededPermission()
    }

    private fun requestNeededPermission() {
        if (ContextCompat.checkSelfPermission(this,
                        android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            android.Manifest.permission.CAMERA)) {
                Toast.makeText(this,
                        "I need it for camera", Toast.LENGTH_SHORT).show()
            }

            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    PERMISSION_REQUEST_CODE)
        } else {
            // már van engedély
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "CAMERA perm granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "CAMERA perm NOT granted", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun attachClick(view: View) {
        startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            data?.let {
                imgAttach.setImageBitmap(it.extras.get("data") as Bitmap)
                imgAttach.visibility = View.VISIBLE
            }
        }
    }


    private fun uploadPost(imageUrl: String = "") {
        val key = FirebaseDatabase.getInstance().reference.child("posts").push().key

        val currentUser = FirebaseAuth.getInstance().currentUser!!

        val post = Post(
                currentUser.uid,
                currentUser.displayName!!,
                etTitle.text.toString(),
                etBody.text.toString(),
                ""
        )

        if (imageUrl.isNotEmpty()) {
            post.imgUrl = imageUrl
        }

        FirebaseDatabase.getInstance().reference.child("posts").child(key).setValue(post)
        Toast.makeText(this, "Post uploaded", Toast.LENGTH_SHORT).show()
        finish()
    }

    @Throws(Exception::class)
    private fun uploadPostWithImage() {
        imgAttach.isDrawingCacheEnabled = true
        imgAttach.buildDrawingCache()
        val bitmap = imgAttach.drawingCache
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageInBytes = baos.toByteArray()

        val storageRef = FirebaseStorage.getInstance().getReference()
        val newImage = URLEncoder.encode(UUID.randomUUID().toString(), "UTF-8") + ".jpg"
        val newImagesRef = storageRef.child("images/$newImage")

        newImagesRef.putBytes(imageInBytes)
                .addOnFailureListener { exception ->
                    Toast.makeText(this@CreatePostActivity, exception.message, Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener { taskSnapshot ->
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                    uploadPost(taskSnapshot.downloadUrl.toString())
                }
    }

    fun sendClick(view: View) {
        if (imgAttach.visibility == View.GONE) {
            uploadPost()
        } else {
            try {
                uploadPostWithImage()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
