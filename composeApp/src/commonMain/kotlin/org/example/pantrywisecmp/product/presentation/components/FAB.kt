package com.example.pantrywise.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.vectorResource
import pantrywisecmp.composeapp.generated.resources.Res
import pantrywisecmp.composeapp.generated.resources.ic_plus


@Composable
fun SpeedDialFab(
    expanded: Boolean,
    onExpandedChange: () -> Unit,
    onAddManual: () -> Unit,
    onAddCamera: () -> Unit,
    visible: Boolean
) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 135f else 0f,
        label = "fab_rotation"
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + scaleIn(),
        exit = fadeOut() + scaleOut()
    ) {
        Column(horizontalAlignment = Alignment.End) {

            // Camera action
            AnimatedVisibility(
                visible = expanded,
                enter = scaleIn(initialScale = 0f) + fadeIn(),
                exit = scaleOut(targetScale = 0f) + fadeOut()
            ) {
                ScaleInOutFloatingActionButton(
                    onClick = onAddCamera,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        Icons.Default.CameraAlt,
                        contentDescription = "Scan product",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Manual add action
            AnimatedVisibility(
                visible = expanded,
                enter = scaleIn(initialScale = 0f) + fadeIn(),
                exit = scaleOut(targetScale = 0f) + fadeOut()
            ) {
                ScaleInOutFloatingActionButton(
                    onClick = onAddManual,
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.List,
                        contentDescription = "Add manually",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            // Main FAB
            FloatingActionButton(
                onClick = onExpandedChange,
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .rotate(rotation)
                    .scaleInOut()
            ) {
                Icon(
                    imageVector = vectorResource(Res.drawable.ic_plus),
                    contentDescription = if (expanded) "Close" else "Add"
                )
            }
        }
    }
}

@Composable
private fun ScaleInOutFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "fab_scale"
    )

    SmallFloatingActionButton(
        onClick = onClick,
        shape = CircleShape,
        modifier = modifier.scale(scale),
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        content()
    }
}

private fun Modifier.scaleInOut() = this.then(
    Modifier.graphicsLayer {
        scaleX = 1f
        scaleY = 1f
        rotationZ = 0f
    }
)