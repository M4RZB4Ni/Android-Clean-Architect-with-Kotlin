package com.marzbani.presentation.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.marzbani.presentation.details.component.DetailsAppBar
import com.marzbani.presentation.details.component.DetailsCardItem


@Composable
fun DetailsScreen(modifier: Modifier = Modifier, viewModel: DetailsViewModel,nodeId:String,navController: NavHostController) {
    LaunchedEffect(viewModel) {
        viewModel.getDetails(nodeId)
    }
    Scaffold(topBar = {
        DetailsAppBar(
            modifier = Modifier
                .padding(16.dp),
            onBackClick = {navController.navigateUp()},
            onEditClick = {},
            onShareClick = {}
        )
    }){
       paddingValues -> Column {

        val details by viewModel.details.collectAsState()
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues).padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Details",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                DetailsCardItem("ID", details.id)
                DetailsCardItem("Created At", details.createdAt)
                DetailsCardItem("Created By", details.createdBy)
                DetailsCardItem("Last Modified At", details.lastModifiedAt)
                DetailsCardItem("Last Modified By", details.lastModifiedBy)

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 4.dp
                    ),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                    )
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                    )
                }

                Text(
                    text = details.description,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }


    }
}