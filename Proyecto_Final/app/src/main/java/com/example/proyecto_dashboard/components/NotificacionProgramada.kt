package com.example.proyecto_dashboard.components

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.proyecto_dashboard.MainActivity
import com.example.proyecto_dashboard.R
import com.example.proyecto_dashboard.utils.Constants.channelId

// Se define una clase llamada NotificacionProgramada que hereda de la clase BroadcastReceiver
class NotificacionProgramada: BroadcastReceiver() {

    //Objeto que no requiere ser instanciado de una clase
    companion object{
        const val NOTIFICATION_ID = 5
    }

    // Se sobrescribe el método onReceive de la clase BroadcastReceiver
    override fun onReceive(
        context: Context,
        intent: Intent?
    ) {
        // Se llama a la función privada crearNotification para crear una notificación
        crearNotification(context)
        TODO("Not yet implemented")
    }

    // Se define una función privada llamada crearNotification que recibe un argumento de tipo Context
    private fun crearNotification(context: Context) {

        // Se crea un objeto Intent para definir la acción que se ejecutará cuando el usuario haga clic en la notificación
        val intent = Intent(
            context, //Primer argumento
            MainActivity::class.java //Segundo argumento
        ).apply {
            // Se definen las banderas del Intent
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        /**
         * Se utiliza el constructor del objeto [NotificationCompat.Builder]
         * para crear un constructor de notificaciones
         */
        val notification = NotificationCompat.Builder(
            context,
            channelId
        )
            .setSmallIcon(R.drawable.ic_shopping_cart_24)
            .setContentTitle("Notificacion Programada")
            .setContentText("Saludos! Aprendiendo a programar notificaciones")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Saludos! esta es una prueba de notificacion " +
                    "programada; aparece despues de un tiempo, incluso " +
                    "si la APP esta cerrada. Podemos utilizar las otras " +
                    "caracteristicas de una notificacion que ya se han utilizado. " +
                    "Da clic para abrir la APP"
                    )
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build() //Construye la Notificacion

        // Obtener el servicio de sistema NotificationManager
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)
        as NotificationManager
        // Llamar al método notify del NotificationManager para mostrar la notificación
        manager.notify(
            NOTIFICATION_ID,
            notification
        )
    }
}