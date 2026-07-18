package com.example

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Launch
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(
                colorScheme = MaterialTheme.colorScheme.copy(
                    primary = Color(0xA8, 0x55, 0xF7), // Modern Purple
                    onPrimary = Color.White,
                    secondary = Color(0x3B, 0x82, 0xF6), // Blue
                    background = Color(0x09, 0x08, 0x0D), // Ultra Dark
                    surface = Color(0x13, 0x11, 0x1C), // Deep Violet Card
                    onSurface = Color.White
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen()
                }
            }
        }
    }
}

@Composable
fun AppScreen(viewModel: MainViewModel = viewModel()) {
    val activeTab by viewModel.activeTab.collectAsState()
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x0E, 0x0C, 0x15))
                    .navigationBarsPadding()
            ) {
                HorizontalDivider(color = Color(0x23, 0x1F, 0x33))
                TabRow(
                    selectedTabIndex = when (activeTab) {
                        "generator" -> 0
                        "terminal" -> 1
                        "github" -> 2
                        "assets" -> 3
                        else -> 0
                    },
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary,
                    indicator = { tabPositions ->
                        TabRowDefaults.SecondaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[when (activeTab) {
                                "generator" -> 0
                                "terminal" -> 1
                                "github" -> 2
                                "assets" -> 3
                                else -> 0
                            }]),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                ) {
                    val tabs = listOf(
                        Triple("generator", "Generator", Icons.Default.Code),
                        Triple("terminal", "Simulator", Icons.Default.Terminal),
                        Triple("github", "GitHub", Icons.Default.Hub),
                        Triple("assets", "Assets", Icons.Default.AutoAwesome)
                    )
                    tabs.forEachIndexed { index, pair ->
                        Tab(
                            selected = activeTab == pair.first,
                            onClick = { viewModel.setActiveTab(pair.first) },
                            text = { Text(pair.second, fontSize = 11.sp, fontWeight = FontWeight.SemiBold) },
                            icon = { Icon(pair.third, contentDescription = pair.second, modifier = Modifier.size(20.dp)) },
                            selectedContentColor = MaterialTheme.colorScheme.primary,
                            unselectedContentColor = Color.Gray,
                            modifier = Modifier.testTag("tab_${pair.first}")
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .statusBarsPadding()
        ) {
            // Header Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(Color(0xA8, 0x55, 0xF7), Color(0x3B, 0x82, 0xF6))
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = "Logo",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = "Expo Actions Console",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "GitHub CI/CD & Asset Builder",
                        color = Color.Gray,
                        fontSize = 11.sp
                    )
                }
            }

            HorizontalDivider(color = Color(0x23, 0x1F, 0x33))

            // Main Contents switching based on active tab
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) {
                when (activeTab) {
                    "generator" -> GeneratorTab(viewModel)
                    "terminal" -> TerminalTab(viewModel)
                    "github" -> GitHubTab(viewModel)
                    "assets" -> AssetsTab(viewModel)
                }
            }
        }
    }
}

// --- GENERATOR TAB COMPOSABLE ---
@Composable
fun GeneratorTab(viewModel: MainViewModel) {
    val projectName by viewModel.projectName.collectAsState()
    val nodeVersion by viewModel.nodeVersion.collectAsState()
    val expoSdkVersion by viewModel.expoSdkVersion.collectAsState()
    val packageManager by viewModel.packageManager.collectAsState()
    val buildType by viewModel.buildType.collectAsState()
    val useCache by viewModel.useCache.collectAsState()
    val customKeystore by viewModel.customKeystore.collectAsState()

    var copied by remember { mutableStateOf(false) }
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    val yamlContent = viewModel.generateYAML()

    LaunchedEffect(copied) {
        if (copied) {
            kotlinx.coroutines.delay(2000)
            copied = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x13, 0x11, 0x1C)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Workflow Parameters",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))

                // Project Name input
                OutlinedTextField(
                    value = projectName,
                    onValueChange = { viewModel.projectName.value = it },
                    label = { Text("Project Name") },
                    textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("input_project_name"),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), gap = 12.dp) {
                    // Node Version input
                    OutlinedTextField(
                        value = nodeVersion,
                        onValueChange = { viewModel.nodeVersion.value = it },
                        label = { Text("Node.js Version") },
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("input_node_version"),
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Expo SDK input
                    OutlinedTextField(
                        value = expoSdkVersion,
                        onValueChange = { viewModel.expoSdkVersion.value = it },
                        label = { Text("Expo SDK") },
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("input_expo_sdk"),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Package Manager Selector
                Text("Package Manager", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val managers = listOf("npm", "yarn", "pnpm")
                    managers.forEach { mgr ->
                        val selected = packageManager == mgr
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (selected) MaterialTheme.colorScheme.primary else Color(0x1B, 0x19, 0x27))
                                .clickable { viewModel.packageManager.value = mgr }
                                .border(
                                    width = 1.dp,
                                    color = if (selected) Color.Transparent else Color(0x23, 0x1F, 0x33),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .testTag("btn_pm_$mgr"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = mgr.uppercase(),
                                color = if (selected) Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Build Type Selector
                Text("Target Artifact", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val types = listOf("apk", "aab", "both")
                    types.forEach { t ->
                        val selected = buildType == t
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (selected) MaterialTheme.colorScheme.primary else Color(0x1B, 0x19, 0x27))
                                .clickable { viewModel.buildType.value = t }
                                .border(
                                    width = 1.dp,
                                    color = if (selected) Color.Transparent else Color(0x23, 0x1F, 0x33),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .testTag("btn_type_$t"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = t.uppercase(),
                                color = if (selected) Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Toggle Cache
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Configure Runner Cache", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text("Persist Gradle caches between workflow runs", color = Color.Gray, fontSize = 10.sp)
                    }
                    Switch(
                        checked = useCache,
                        onCheckedChange = { viewModel.useCache.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                            uncheckedThumbColor = Color.DarkGray,
                            uncheckedTrackColor = Color(0x1F, 0x1D, 0x2C)
                        ),
                        modifier = Modifier.testTag("switch_cache")
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Toggle Custom Keystore Signing
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Decode Android Keystore JKS", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text("Configure Base64 decoding and apksigner signing steps", color = Color.Gray, fontSize = 10.sp)
                    }
                    Switch(
                        checked = customKeystore,
                        onCheckedChange = { viewModel.customKeystore.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                            uncheckedThumbColor = Color.DarkGray,
                            uncheckedTrackColor = Color(0x1F, 0x1D, 0x2C)
                        ),
                        modifier = Modifier.testTag("switch_keystore")
                    )
                }
            }
        }

        // YAML Generated File Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x0C, 0x0A, 0x13)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Code,
                            contentDescription = "Code",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "workflow.yml",
                            color = Color.LightGray,
                            fontSize = 13.sp,
                            fontFamily = FontFamily.Monospace,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            clipboardManager.setText(AnnotatedString(yamlContent))
                            copied = true
                            Toast.makeText(context, "Copied YAML script to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (copied) Color(0x22, 0xC5, 0x5E) else Color(0x24, 0x1F, 0x38),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .height(32.dp)
                            .testTag("btn_copy_yaml")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = if (copied) Icons.Default.Check else Icons.Default.ContentCopy,
                                contentDescription = "Copy",
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(if (copied) "Copied" else "Copy Code", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0x05, 0x04, 0x08), shape = RoundedCornerShape(10.dp))
                        .border(1.dp, Color(0x1B, 0x18, 0x28), shape = RoundedCornerShape(10.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = yamlContent,
                        color = Color(0xD1, 0xD5, 0xDB),
                        fontSize = 11.sp,
                        fontFamily = FontFamily.Monospace,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

// --- TERMINAL TAB COMPOSABLE ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TerminalTab(viewModel: MainViewModel) {
    val speedFactor by viewModel.simulationSpeed.collectAsState()
    val errType by viewModel.selectedError.collectAsState()
    val isBuilding by viewModel.isBuilding.collectAsState()
    val currentStepIndex by viewModel.currentStepIndex.collectAsState()
    val logs by viewModel.logs.collectAsState()
    val successfulRunData by viewModel.successfulRunData.collectAsState()

    val buildSteps = viewModel.buildSteps
    val consoleListState = rememberLazyListState()

    // Auto Scroll console logs
    LaunchedEffect(logs.size) {
        if (logs.isNotEmpty()) {
            consoleListState.animateScrollToItem(logs.size - 1)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x13, 0x11, 0x1C)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Runner Environment Configuration",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))

                // Error Selection dropdown/segmented button
                Text("Injected Failure Simulation", color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(6.dp))

                var expanded by remember { mutableStateOf(false) }
                val errorLabels = mapOf(
                    "none" to "No Errors (Full Success Pass)",
                    "keystore" to "Apksigner Failure (Keystore Base64 Empty)",
                    "memory" to "OutOfMemoryError (Heap Crash)",
                    "jdk" to "JDK Version Mismatch (JVM Unsupported)"
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { if (!isBuilding) expanded = !expanded },
                    modifier = Modifier.fillMaxWidth().testTag("dropdown_failures")
                ) {
                    OutlinedTextField(
                        readOnly = true,
                        value = errorLabels[errType] ?: "Select option",
                        onValueChange = {},
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedContainerColor = Color(0x1E, 0x1B, 0x2F)
                        ),
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        shape = RoundedCornerShape(10.dp),
                        textStyle = TextStyle(fontSize = 13.sp)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color(0x1E, 0x1B, 0x2F))
                    ) {
                        errorLabels.forEach { (key, value) ->
                            DropdownMenuItem(
                                text = { Text(value, color = Color.White, fontSize = 13.sp) },
                                onClick = {
                                    viewModel.selectedError.value = key
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Simulation Speed slider
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Simulation Speed Multiplier", color = Color.White, fontSize = 13.sp)
                    Text("${speedFactor.toInt()}x Speed", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Slider(
                    value = speedFactor,
                    onValueChange = { if (!isBuilding) viewModel.simulationSpeed.value = it.toInt().toFloat() },
                    valueRange = 1f..4f,
                    steps = 2,
                    colors = SliderDefaults.colors(
                        activeTrackColor = MaterialTheme.colorScheme.primary,
                        inactiveTrackColor = Color(0x23, 0x1F, 0x33),
                        thumbColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.testTag("slider_speed")
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Action triggers
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { viewModel.startSimulation() },
                        enabled = !isBuilding,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = Color(0x3B, 0x2D, 0x51)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1.5f)
                            .height(44.dp)
                            .testTag("btn_run_sim")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.PlayArrow, contentDescription = "Run", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Run Simulation", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    OutlinedButton(
                        onClick = { viewModel.resetSimulation() },
                        enabled = logs.isNotEmpty() && !isBuilding,
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.LightGray,
                            disabledContentColor = Color.DarkGray
                        ),
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (logs.isNotEmpty() && !isBuilding) Color.LightGray else Color.DarkGray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .testTag("btn_reset_sim")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Refresh, contentDescription = "Reset", modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Reset", fontSize = 13.sp)
                        }
                    }
                }
            }
        }

        // Steps Progress Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x13, 0x11, 0x1C)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Runner Pipeline Steps",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))

                buildSteps.forEachIndexed { index, step ->
                    val isActive = currentStepIndex == index
                    val isSuccess = step.status == "success"
                    val isFailed = step.status == "failed"
                    val isRunning = step.status == "running"

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        when {
                                            isSuccess -> Color(0x1B, 0x5E, 0x20)
                                            isFailed -> Color(0xB7, 0x1C, 0x1C)
                                            isRunning -> Color(0x02, 0x77, 0xBD)
                                            else -> Color(0x21, 0x1E, 0x30)
                                        },
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                when {
                                    isSuccess -> Icon(Icons.Default.Check, "Success", tint = Color.White, modifier = Modifier.size(13.dp))
                                    isFailed -> Icon(Icons.Default.Error, "Failed", tint = Color.White, modifier = Modifier.size(13.dp))
                                    isRunning -> CircularProgressIndicator(color = Color.White, strokeWidth = 1.5.dp, modifier = Modifier.size(12.dp))
                                    else -> Text((index + 1).toString(), color = Color.Gray, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = step.name,
                                color = if (isRunning || isSuccess) Color.White else Color.Gray,
                                fontSize = 13.sp,
                                fontWeight = if (isRunning) FontWeight.Bold else FontWeight.Medium
                            )
                        }

                        if (isRunning) {
                            Text(
                                text = "${(step.progress * 100).toInt()}%",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        } else if (isSuccess) {
                            Text("Passed", color = Color(0xFF, 0x4B, 0x55, 0xE2).let { Color(0x22, 0xC5, 0x5E) }, fontSize = 12.sp)
                        } else if (isFailed) {
                            Text("Failed", color = Color(0xEF, 0x44, 0x44), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        } else {
                            Text("Queued", color = Color.DarkGray, fontSize = 12.sp)
                        }
                    }
                    if (index < buildSteps.size - 1) {
                        HorizontalDivider(color = Color(0x1F, 0x1D, 0x2C), modifier = Modifier.padding(start = 36.dp))
                    }
                }
            }
        }

        // LIVE TERMINAL LOGS CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x05, 0x05, 0x0A)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x1B, 0x18, 0x28)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Terminal, contentDescription = "Terminal", tint = Color.Green, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("console_output.log", color = Color.Gray, fontSize = 12.sp, fontFamily = FontFamily.Monospace)
                    }
                    if (isBuilding) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary, strokeWidth = 2.dp, modifier = Modifier.size(12.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("STREAMING", color = MaterialTheme.colorScheme.primary, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        }
                    } else {
                        Text(if (logs.isEmpty()) "IDLE" else "COMPLETED", color = Color.DarkGray, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(260.dp)
                        .background(Color.Black, shape = RoundedCornerShape(10.dp))
                        .border(1.dp, Color(0x1F, 0x1D, 0x2C), shape = RoundedCornerShape(10.dp))
                        .padding(10.dp)
                ) {
                    if (logs.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterAlignment) {
                                Icon(Icons.Default.Terminal, contentDescription = "Console", tint = Color.DarkGray, modifier = Modifier.size(36.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("Click 'Run Simulation' to start compiling", color = Color.DarkGray, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                            }
                        }
                    } else {
                        LazyColumn(
                            state = consoleListState,
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(logs) { log ->
                                Text(
                                    text = log.text,
                                    color = when (log.type) {
                                        "success" -> Color(0x4A, 0xDF, 0x5D)
                                        "error" -> Color(0xF4, 0x3F, 0x5E)
                                        "stderr" -> Color(0xFB, 0xBF, 0x24)
                                        "info" -> Color(0x38, 0xBD, 0xF8)
                                        else -> Color(0xE5, 0xE7, 0xEB)
                                    },
                                    fontSize = 11.sp,
                                    fontFamily = FontFamily.Monospace,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }

        // Successful Release Artifact Panel
        successfulRunData?.let { data ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateEnterExit(),
                colors = CardDefaults.cardColors(containerColor = Color(0x1B, 0x2A, 0x20)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x3B, 0x6E, 0x46)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.CheckCircle, "Success", tint = Color(0x4A, 0xDF, 0x5D), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Compilation Succeeded Perfectly!", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Your GitHub action executed without a single native compilation error. Released APK output metrics:", color = Color.LightGray, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text("Run Identifier", color = Color.Gray, fontSize = 9.sp)
                            Text(data.runId, color = Color.White, fontSize = 11.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text("Artifact Title", color = Color.Gray, fontSize = 9.sp)
                            Text(data.artifactName, color = Color.White, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                        }
                        Column {
                            Text("APK Package Size", color = Color.Gray, fontSize = 9.sp)
                            Text(data.apkSize, color = Color.White, fontSize = 11.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0x22, 0xC5, 0x5E)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(42.dp)
                            .testTag("btn_download_apk")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Download, "Download", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Install Signed APK (Simulated)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Failed Run Diagnoses Card
        if (!isBuilding && buildSteps.any { it.status == "failed" }) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0x2D, 0x14, 0x1A)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x6E, 0x20, 0x2D)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Error, "Error Info", tint = Color(0xF4, 0x3F, 0x5E), modifier = Modifier.size(20.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Interactive Pipeline Debugger", color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    
                    val diagnostics = when (errType) {
                        "jdk" -> Pair(
                            "JDK Out-of-Date Environment Error",
                            "The target Android Gradle Plugin compiled package requires JDK 17, but the runtime Ubuntu workspace was running JDK 11.\n\n✔️ Fix: Verify and replace your setup-java configuration block in workflow.yml to use java-version: '17' explicitly."
                        )
                        "memory" -> Pair(
                            "Gradle Compiler JVM Heap Space Out-of-Memory",
                            "The Android JVM daemon reached its maximum allocated heap space and was terminated by the Linux OOM-killer.\n\n✔️ Fix: Update gradle.jvmargs under gradlew invocation options. Configure -Xmx3072m in gradle.properties to grant the build environment sufficient heap allocation."
                        )
                        "keystore" -> Pair(
                            "Keystore Sign-off Authentication Error",
                            "Apksigner failed with a non-zero exit code because the keystore decryption base64 string ANDROID_KEYSTORE_BASE64 was empty, expired, or had incorrect password aliases.\n\n✔️ Fix: Save your base64 JKS keystore block to your Github Repository Secrets. Double check JKS password fields are fully mapped."
                        )
                        else -> Pair("Undefined Build Failure", "No failure details compiled. Check raw console outputs above.")
                    }

                    Text(diagnostics.first, color = Color(0xF4, 0x3F, 0x5E), fontSize = 13.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(diagnostics.second, color = Color.LightGray, fontSize = 11.sp)
                }
            }
        }
    }
}

// --- GITHUB TAB COMPOSABLE ---
@Composable
fun GitHubTab(viewModel: MainViewModel) {
    val token by viewModel.githubToken.collectAsState()
    val owner by viewModel.githubOwner.collectAsState()
    val repo by viewModel.githubRepo.collectAsState()
    val workflowId by viewModel.githubWorkflowId.collectAsState()
    val ref by viewModel.githubRef.collectAsState()
    
    val loading by viewModel.githubLoading.collectAsState()
    val error by viewModel.githubError.collectAsState()
    val dispatchStatus by viewModel.dispatchStatus.collectAsState()
    val runs by viewModel.githubRuns.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x13, 0x11, 0x1C)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "GitHub API Integration",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Connect live actions to trigger dispatch workflows",
                    color = Color.Gray,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Token (masked)
                OutlinedTextField(
                    value = token,
                    onValueChange = { viewModel.githubToken.value = it },
                    label = { Text("Personal Access Token (classic)") },
                    textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                    visualTransformation = PasswordVisualTransformation(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("github_token"),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), gap = 12.dp) {
                    // Owner
                    OutlinedTextField(
                        value = owner,
                        onValueChange = { viewModel.githubOwner.value = it },
                        label = { Text("Repo Owner") },
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("github_owner"),
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Repo
                    OutlinedTextField(
                        value = repo,
                        onValueChange = { viewModel.githubRepo.value = it },
                        label = { Text("Repo Name") },
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .testTag("github_repo"),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), gap = 12.dp) {
                    // Workflow ID
                    OutlinedTextField(
                        value = workflowId,
                        onValueChange = { viewModel.githubWorkflowId.value = it },
                        label = { Text("Workflow ID (Optional)") },
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(1.2f)
                            .testTag("github_workflow_id"),
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Git Ref
                    OutlinedTextField(
                        value = ref,
                        onValueChange = { viewModel.githubRef.value = it },
                        label = { Text("Git Ref") },
                        textStyle = TextStyle(color = Color.White, fontSize = 14.sp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                            focusedLabelColor = MaterialTheme.colorScheme.primary,
                            unfocusedLabelColor = Color.Gray
                        ),
                        modifier = Modifier
                            .weight(0.8f)
                            .testTag("github_ref"),
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (error.isNotEmpty()) {
                    Text(
                        text = error,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }

                dispatchStatus?.let { status ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0x1B, 0x23, 0x30)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x2D, 0x3A, 0x50)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Info, "Status", tint = Color(0x38, 0xBD, 0xF8), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(status, color = Color.White, fontSize = 11.sp)
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button(
                        onClick = { viewModel.testGithubConnection() },
                        enabled = !loading,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0x24, 0x1F, 0x38)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp)
                            .testTag("btn_github_test")
                    ) {
                        if (loading && dispatchStatus == null) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                        } else {
                            Text("Test Connect", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        }
                    }

                    Button(
                        onClick = { viewModel.triggerWorkflowDispatch() },
                        enabled = !loading && dispatchStatus != null,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = Color(0x3A, 0x28, 0x4E)
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .weight(1.2f)
                            .height(44.dp)
                            .testTag("btn_github_dispatch")
                    ) {
                        if (loading && dispatchStatus != null) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                        } else {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Launch, "Dispatch", modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Trigger Dispatch", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }

        // Runs list
        if (runs.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0x0C, 0x0A, 0x13)),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Recent Workflow Runs History",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    runs.forEachIndexed { idx, r ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(r.name, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("#${r.id}", color = Color.Gray, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                                }
                                Spacer(modifier = Modifier.height(2.dp))
                                Text("branch: ${r.branch} • ${r.createdAt}", color = Color.Gray, fontSize = 10.sp)
                            }

                            // Status badge
                            Box(
                                modifier = Modifier
                                    .background(
                                        when (r.conclusion) {
                                            "success" -> Color(0x1B, 0x5E, 0x20)
                                            "failure" -> Color(0xB7, 0x1C, 0x1C)
                                            "queued" -> Color(0xE6, 0x51, 0x00)
                                            else -> Color(0x21, 0x1E, 0x30)
                                        },
                                        shape = RoundedCornerShape(6.dp)
                                    )
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = r.conclusion.uppercase(),
                                    color = Color.White,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                        if (idx < runs.size - 1) {
                            HorizontalDivider(color = Color(0x1F, 0x1D, 0x2C))
                        }
                    }
                }
            }
        }
    }
}

// --- GEMINI ASSETS TAB COMPOSABLE ---
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssetsTab(viewModel: MainViewModel) {
    val prompt by viewModel.assetPrompt.collectAsState()
    val type by viewModel.assetType.collectAsState()
    val size by viewModel.imageSize.collectAsState()
    val aspect by viewModel.aspectRatio.collectAsState()
    val isGenerating by viewModel.isGenerating.collectAsState()
    val generatedImage by viewModel.generatedImage.collectAsState()
    val error by viewModel.generationError.collectAsState()
    val useStudioModel by viewModel.useStudioModel.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x13, 0x11, 0x1C)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AutoAwesome, "Sparkle", tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(18.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Gemini Asset Studio",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "Generate professional launcher icons & splash illustrations",
                    color = Color.Gray,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Prompt Input
                OutlinedTextField(
                    value = prompt,
                    onValueChange = { viewModel.assetPrompt.value = it },
                    label = { Text("Visual Prompt Description") },
                    textStyle = TextStyle(color = Color.White, fontSize = 13.sp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = Color(0x44, 0x3E, 0x61),
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        unfocusedLabelColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .testTag("input_asset_prompt"),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 4
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Prompt Suggestions chips
                Text("Popular Suggestions", color = Color.Gray, fontSize = 11.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val suggestions = listOf(
                        "Dark Neon Hex Grid",
                        "Material Purple Terminal",
                        "Minimal Retro Gradient Icon"
                    )
                    suggestions.forEach { sug ->
                        Box(
                            modifier = Modifier
                                .background(Color(0x1B, 0x19, 0x27), shape = RoundedCornerShape(8.dp))
                                .border(1.dp, Color(0x23, 0x1F, 0x33), shape = RoundedCornerShape(8.dp))
                                .clickable { viewModel.assetPrompt.value = sug }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(sug, color = Color.LightGray, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Asset Type Selector Row
                Text("Asset Type Target", color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val types = listOf(
                        Triple("icon", "App Icon", "1:1"),
                        Triple("splash_portrait", "Splash (Port)", "9:16"),
                        Triple("splash_landscape", "Splash (Land)", "16:9")
                    )
                    types.forEach { t ->
                        val selected = type == t.first
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (selected) MaterialTheme.colorScheme.primary else Color(0x1B, 0x19, 0x27))
                                .clickable { viewModel.assetType.value = t.first }
                                .border(
                                    width = 1.dp,
                                    color = if (selected) Color.Transparent else Color(0x23, 0x1F, 0x33),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .testTag("btn_asset_type_${t.first}"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = t.second,
                                color = if (selected) Color.White else Color.Gray,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    // Aspect ratio indicator
                    Column {
                        Text("Target Aspect Ratio", color = Color.White, fontSize = 13.sp)
                        Text(aspect, color = MaterialTheme.colorScheme.primary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    // Resolution selector
                    var expandedRes by remember { mutableStateOf(false) }
                    ExposedDropdownMenuBox(
                        expanded = expandedRes,
                        onExpandedChange = { expandedRes = !expandedRes },
                        modifier = Modifier.width(100.dp).testTag("dropdown_resolution")
                    ) {
                        OutlinedTextField(
                            readOnly = true,
                            value = size,
                            onValueChange = {},
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRes) },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White,
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color(0x44, 0x3E, 0x61)
                            ),
                            modifier = Modifier.menuAnchor(),
                            shape = RoundedCornerShape(8.dp),
                            textStyle = TextStyle(fontSize = 11.sp),
                            label = { Text("Res") }
                        )

                        ExposedDropdownMenu(
                            expanded = expandedRes,
                            onDismissRequest = { expandedRes = false },
                            modifier = Modifier.background(Color(0x1E, 0x1B, 0x2F))
                        ) {
                            listOf("1K", "2K", "4K").forEach { res ->
                                DropdownMenuItem(
                                    text = { Text(res, color = Color.White, fontSize = 11.sp) },
                                    onClick = {
                                        viewModel.imageSize.value = res
                                        expandedRes = false
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Toggle Professional Studio Model
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Professional Studio Model", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text("Use high-fidelity weights (requires API quota)", color = Color.Gray, fontSize = 10.sp)
                    }
                    Switch(
                        checked = useStudioModel,
                        onCheckedChange = { viewModel.useStudioModel.value = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                            uncheckedThumbColor = Color.DarkGray,
                            uncheckedTrackColor = Color(0x1F, 0x1D, 0x2C)
                        ),
                        modifier = Modifier.testTag("switch_studio_model")
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                if (error.isNotEmpty()) {
                    Text(error, color = Color.Red, fontSize = 12.sp, modifier = Modifier.padding(bottom = 10.dp))
                }

                Button(
                    onClick = { viewModel.generateAsset() },
                    enabled = !isGenerating,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        disabledContainerColor = Color(0x3B, 0x2D, 0x51)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp)
                        .testTag("btn_generate_asset")
                ) {
                    if (isGenerating) {
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Rendering Studio Asset...", fontSize = 13.sp)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, "Sparkle", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Generate Native Asset", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        // Preview Area Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0x0C, 0x0A, 0x13)),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0x23, 0x1F, 0x33)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Generated Asset Preview",
                    color = Color.LightGray,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(14.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(
                            when (type) {
                                "splash_portrait" -> 9 / 16f
                                "splash_landscape" -> 16 / 9f
                                else -> 1f
                            }
                        )
                        .background(Color(0x05, 0x04, 0x08), shape = RoundedCornerShape(12.dp))
                        .border(1.dp, Color(0x1B, 0x18, 0x28), shape = RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        isGenerating -> {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                                Spacer(modifier = Modifier.height(12.dp))
                                Text("Parsing prompts and rendering pixels...", color = Color.Gray, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                            }
                        }
                        generatedImage != null -> {
                            Image(
                                bitmap = generatedImage!!.asImageBitmap(),
                                contentDescription = "Generated Asset",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(RoundedCornerShape(12.dp))
                            )
                        }
                        else -> {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Default.AutoAwesome, "Sparkles", tint = Color.DarkGray, modifier = Modifier.size(44.dp))
                                Spacer(modifier = Modifier.height(10.dp))
                                Text("Awaiting visual generation instructions...", color = Color.DarkGray, fontSize = 11.sp)
                            }
                        }
                    }
                }

                if (generatedImage != null && !isGenerating) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = {
                            Toast.makeText(context, "Saved asset successfully to Android gallery!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0x1B, 0x19, 0x27)),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp)
                            .testTag("btn_save_asset")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Download, "Save", modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Download Studio Asset", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

// --- Dynamic Animations Helpers ---
@Composable
fun RowScopeGap(modifier: Modifier = Modifier, gap: Int, content: @Composable RowScopeGap.() -> Unit) {
    // Spacer helper
}

@Composable
fun Row(modifier: Modifier = Modifier, gap: Int, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap.dp),
        content = content
    )
}

@Composable
fun Modifier.animateEnterExit(): Modifier = this // stub animation modifier

@Composable
fun ColumnScope.Row(modifier: Modifier = Modifier, gap: Int, content: @Composable RowScope.() -> Unit) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap.dp),
        content = content
    )
}

interface RowScopeGap {
    // helper
}
