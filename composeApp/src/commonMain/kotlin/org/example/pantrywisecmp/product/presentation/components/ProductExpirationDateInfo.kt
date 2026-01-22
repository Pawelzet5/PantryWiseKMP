package org.example.pantrywisecmp.product.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.example.pantrywisecmp.product.presentation.helper.DateTimeHelper
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.ic_hourglass
import pantrywisecmp.composeapp.generated.resources.product_expiration_date_future
import pantrywisecmp.composeapp.generated.resources.product_expiration_date_past
import pantrywisecmp.composeapp.generated.resources.product_expiration_date_today
import kotlin.math.abs


@Composable
fun ProductExpirationDateInfo(expirationTimeStamp: Long, isBackgroundColorDefault: Boolean) {
    val daysToExpiration = DateTimeHelper.daysBetweenTimestampAndNow(expirationTimeStamp)
    val color = when {
        daysToExpiration < 0 -> MaterialTheme.colorScheme.error
        !isBackgroundColorDefault -> MaterialTheme.colorScheme.onPrimary
        else -> MaterialTheme.colorScheme.onPrimaryContainer
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_hourglass),
            tint = color,
            contentDescription = null,
            modifier = Modifier
                .size(10.dp)
        )
        Text(
            text = expirationText(daysToExpiration),
            color = color,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun expirationText(daysToExpiration: Int): String {
    return when {
        daysToExpiration < 0 -> {
            val days = abs(daysToExpiration)
            stringResource(
                Res.string.product_expiration_date_past,
                days
            )
        }
        daysToExpiration == 0 -> {
            stringResource(Res.string.product_expiration_date_today)
        }
        else -> {
            stringResource(
                Res.string.product_expiration_date_future,
                daysToExpiration
            )
        }
    }
}
