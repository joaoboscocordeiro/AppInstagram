package br.com.multalpha.aplicativos.v1.appinstagram.ui.register.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import br.com.multalpha.aplicativos.v1.appinstagram.R
import br.com.multalpha.aplicativos.v1.appinstagram.common.base.DependencyInjector
import br.com.multalpha.aplicativos.v1.appinstagram.databinding.FragmentRegisterPhotoBinding
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.RegisterPhoto
import br.com.multalpha.aplicativos.v1.appinstagram.ui.register.presentation.RegisterPhotoPresenter
import br.com.multalpha.aplicativos.v1.appinstagram.util.CropperImageFragment
import br.com.multalpha.aplicativos.v1.appinstagram.util.CustomDialog

/**
 * Created by João Bosco on 20/10/2021.
 */

class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo), RegisterPhoto.View {

    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null

    override lateinit var presenter: RegisterPhoto.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { requestKey, bundle ->
            val uri = Bundle().getParcelable<Uri>(CropperImageFragment.KEY_URI)
            onCropImageResult(uri)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoBinding.bind(view)

        val repository = DependencyInjector.registerEmailRepository()
        presenter = RegisterPhotoPresenter(this, repository)

        binding?.let {
            with(it) {
                registerBtnJump.setOnClickListener {
                    fragmentAttachListener?.goToMainScreen()
                }

                registerBtnAddPhoto.isEnabled = true
                registerBtnAddPhoto.setOnClickListener {
                    openDialog()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListener) {
            fragmentAttachListener = context
        }
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnAddPhoto?.showProgress(enabled)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess() {
        fragmentAttachListener?.goToMainScreen()
    }

    private fun openDialog() {
        val customDialog = CustomDialog(requireContext())
        customDialog.addButton(R.string.photo, R.string.gallery) {
            when (it.id) {
                R.string.photo -> {
                    fragmentAttachListener?.goToCameraScreen()
                }
                R.string.gallery -> {
                    fragmentAttachListener?.goToGalleryScreen()
                }
            }
        }
        customDialog.show()
    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            val bitmap = if (Build.VERSION.SDK_INT >= 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding?.registerImgProfile?.setImageBitmap(bitmap)
            presenter.updateUser(uri)
        }
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }
}