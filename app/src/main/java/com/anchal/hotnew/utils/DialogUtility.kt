package com.anchal.hotnew.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.anchal.hotnew.R


class DialogUtility {


    private var progressDialog: AlertDialog? = null

    companion object {
        private var utility: DialogUtility? = null
        fun getInstance(): DialogUtility? {
            if (utility == null)
                utility = DialogUtility()


            return utility
        }
    }


    @SuppressLint("InflateParams")
    fun showProgressDialog(context: AppCompatActivity?, msg: String) {
        if (context != null && (progressDialog == null || !progressDialog!!.isShowing)) {
            val dialogBuilder = AlertDialog.Builder(context)
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val dialogView = inflater.inflate(R.layout.progress_dialog, null)
            val loadingMsg = dialogView.findViewById<TextView>(R.id.loading_msg)
            loadingMsg.setText(msg)
            dialogBuilder.setView(dialogView)
            dialogBuilder.setCancelable(false)
            progressDialog = dialogBuilder.create()
            if (!context.isFinishing)
                progressDialog?.show()
        }
    }

    fun hideProgressDialog() {
        if (progressDialog != null && progressDialog!!.isShowing)
            progressDialog?.dismiss()
    }

    fun permissionSettingDialog(context: Context?, message: String, isMandatory: Boolean) {
        if (context != null) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.resources.getString(R.string.app_name))
            builder.setCancelable(false)
            builder.setMessage(message)
            builder.setPositiveButton("Settings") { dialog, _ ->
                context.startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", context.packageName, null)))
                dialog.dismiss()
            }
            if (isMandatory) {
                builder.setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            }
            builder.show()
        }
    }


    /*fun createDatePicker(context: Context, listener: OnDateSelectListener, minDate: String, maxDate: String) {
        val cal = Calendar.getInstance()
        cal.timeInMillis = System.currentTimeMillis()
        val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth -> listener.onDateSelected( dayOfMonth.toString()+ "-" + (month + 1) + "-" + year.toString()) }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))


        if (!minDate.isEmpty()) {
            try {
                val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                val date = format.parse(minDate)
                val calendar = Calendar.getInstance()
                calendar.time = date
                datePickerDialog.datePicker.minDate = calendar.timeInMillis
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }


        if (!maxDate.isEmpty()) {
            try {
                val format = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
                val date = format.parse(maxDate)
                val calendar = Calendar.getInstance()
                calendar.time = date
                datePickerDialog.datePicker.maxDate = calendar.timeInMillis
            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }

        datePickerDialog.show()
    }

    fun showAutoStartDialog(context:BaseActivity?,onDialogClickListener: OnDialogClickListener){
        if(context!=null) {
            val alertDialog = AlertDialog.Builder(context).apply {
                setIcon(R.mipmap.ic_launcher_foreground)
                setTitle(context.getString(R.string.notification_issue))
                setCancelable(false)
                setMessage(context.getString(R.string.enable_auto_start))
                setPositiveButton("OK") { p0, p1 -> onDialogClickListener.onPositiveClick("OK")
                }
            }

            if(!context.isFinishing){
            val dialog = alertDialog.create()
                dialog.show()
            }

        }
    }*/


}