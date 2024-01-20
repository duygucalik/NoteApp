package com.example.noteapp.ui.theme.detail

import android.graphics.drawable.ColorDrawable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.ui.theme.Purple80
import com.example.noteapp.ui.theme.data.Notes
import com.google.android.gms.wallet.button.ButtonConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetail(note : Notes) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 2.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 15.dp),

        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                text = "${note.title}",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                modifier = Modifier.padding(2.dp),
                text = "${note.note}",
                fontFamily = FontFamily.SansSerif,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )

        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 100.dp).height(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Blue

            ),
            onClick = { /*TODO*/ }) {
           // Text(text = "Complate")
            Icon(imageVector = Icons.Filled.Done , contentDescription =    "Done Icon" )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp).height(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Red

            ),
            onClick = { /*TODO*/ }) {
            // Text(text = "Complate")
            Icon(imageVector = Icons.Filled.Delete , contentDescription =    "Delete Icon" )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        }

    }
}