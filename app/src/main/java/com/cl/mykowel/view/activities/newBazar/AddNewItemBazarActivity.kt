package com.cl.mykowel.view.activities.newBazar

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.cl.mykowel.R
import com.cl.mykowel.databinding.ActivityAddNewItemBazarBinding
import com.cl.mykowel.models.model_itemBazar.AddNewItemBazar
import com.google.android.material.textfield.TextInputEditText

class AddNewItemBazarActivity : AppCompatActivity() {
    private var oldDrawable: Drawable? = null
    private var viewModel: AddNewItemBazarActivityViewModel? = null
    private var binding: ActivityAddNewItemBazarBinding? = null
    private var title: TextInputEditText? = null
    private var description: TextInputEditText? = null
    private var price: TextInputEditText? = null
    private var buttonAddPhoto: Button? = null
    private var buttonSend: Button? = null
    private val PICK_IMAGE_REQUEST = 1
    private val PERMISSION_REQUEST_CODE = 100
    private var spinner: Spinner? = null
    private var currentCategory:String="0"
    private var imageUri: Uri? = null
    private var imageView: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewItemBazarBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar?.title = "Новий товар"

        price = findViewById(R.id.priceTxtIET)
        title = binding!!.titleTxtIET
        description = binding!!.descriptionTxtIET
        imageView = findViewById(R.id.imageAddItemBazar)
        oldDrawable = imageView?.drawable

        buttonAddPhoto = findViewById(R.id.btn_add_photo)
        spinner = binding?.spCategory
        buttonAddPhoto!!.setOnClickListener(View.OnClickListener {
            openGallery()
        })
        buttonSend = findViewById(R.id.btn_send_item_bazar)
        buttonSend!!.setOnClickListener(View.OnClickListener {
            createItemBazar()
        })
        initViewModel()
        spinner?.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(parent?.getItemAtPosition(position).toString()){
                    "Виберіть категорію" -> currentCategory = "0"
                    "Одяг і взуття" -> currentCategory = "0134"
                    "Їжа та продукти" -> currentCategory = "0179"
                    "Для саду та городу" -> currentCategory = "0718"
                    "Товари для дітей" -> currentCategory = "0755"
                    "Товари для побуту" -> currentCategory = "0759"
                    "Електроніка та механіка" -> currentCategory = "0372"
                    "Нерухомість" -> currentCategory = "0910"
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                currentCategory = "0"
            }
        }
    }

    private fun openGallery() {
// Перевірка дозволів
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Доступ є, відкриваємо галерею
            val intent = Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        } else {
            // Доступу немає, запитуємо користувача
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        }
    }

    // Обробка запиту дозволу
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Дозвіл надано, відкриваємо галерею
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    startActivityForResult(intent, PICK_IMAGE_REQUEST)
                } else {
                    // Дозвіл не надано, повідомляємо користувача
                    Toast.makeText(
                        this,
                        "Дозвіл на доступ до галереї не надано",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                return
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
            imageUri = data.data
            imageView!!.setImageURI(imageUri)
        }
    }

    private fun getPathFromUri(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)
        cursor?.use {
            val columnIndex = it.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            it.moveToFirst()
            return it.getString(columnIndex)
        }
        return null
    }

    private fun createItemBazar() {
        if(currentCategory == "0"){
            Toast.makeText(this, "Виберіть категорію", Toast.LENGTH_SHORT).show()
            return
        }

        if(title?.text.isNullOrEmpty()){
            Toast.makeText(this, "Введіть заголовок", Toast.LENGTH_SHORT).show()
            return
        }
        if(description?.text.isNullOrEmpty()){
            Toast.makeText(this, "Введіть опис", Toast.LENGTH_SHORT).show()
            return
        }
        if(price?.text.isNullOrEmpty()){
            Toast.makeText(this, "Введіть ціну", Toast.LENGTH_SHORT).show()
            return
        }
        if(oldDrawable == imageView?.drawable){
            Toast.makeText(this, "Виберіть фото", Toast.LENGTH_SHORT).show()
            return
        }
        val itemBazar = AddNewItemBazar(
            title!!.text.toString(),
            description!!.text.toString(),
            price!!.text.toString(),
            currentCategory,
            getPathFromUri(imageUri!!)!!

        )

        try{
            viewModel!!.addItemBazar(this@AddNewItemBazarActivity, itemBazar)
        }catch (e:Exception){
            Toast.makeText(this,"Помилка",Toast.LENGTH_SHORT).show()
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[AddNewItemBazarActivityViewModel::class.java]
    }
}