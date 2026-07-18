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
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

object Loc {
    fun t(key: String, lang: String): String {
        val ar = mapOf(
            "app_title" to "لوحة تحكم إكسبو",
            "app_subtitle" to "بناء الأصول والتكامل المستمر لجيت هاب",
            "tab_generator" to "المولد",
            "tab_terminal" to "المحاكي",
            "tab_github" to "جيت هاب",
            "tab_assets" to "الأصول",
            "workflow_params" to "معاملات سير العمل",
            "project_name" to "اسم المشروع",
            "node_version" to "إصدار Node.js",
            "expo_sdk" to "إصدار إكسبو SDK",
            "package_mgr" to "مدير الحزم",
            "target_artifact" to "القطعة المستهدفة",
            "config_cache" to "تكوين ذاكرة تخزين مؤقت",
            "config_cache_desc" to "الاحتفاظ بذاكرة تخزين مؤقت لـ Gradle بين عمليات سير العمل",
            "decode_keystore" to "فك تشفير مستودع المفاتيح JKS",
            "decode_keystore_desc" to "تهيئة خطوات فك التشفير Base64 والتوقيع",
            "copy_code" to "نسخ الكود",
            "copied" to "تم النسخ",
            "copied_toast" to "تم نسخ برنامج YAML البرمجي إلى الحافظة!",
            "runner_env_config" to "تكوين بيئة التشغيل",
            "injected_failure" to "محاكاة الفشل المحقون",
            "sim_speed" to "مضاعف سرعة المحاكاة",
            "sim_speed_val" to "سرعة",
            "run_sim" to "بدء المحاكاة",
            "reset" to "إعادة ضبط",
            "runner_pipeline" to "خطوات التشغيل",
            "passed" to "ناجح",
            "failed" to "فشل",
            "queued" to "في الانتظار",
            "compilation_success" to "نجح البناء بشكل مثالي!",
            "compilation_success_desc" to "تم تنفيذ إجراء GitHub الخاص بك دون أي خطأ في البناء. مقاييس مخرجات APK الصادرة:",
            "run_id" to "معرف التشغيل",
            "artifact_title" to "عنوان الملف",
            "apk_size" to "حجم حزمة APK",
            "install_apk_sim" to "تثبيت ملف APK الموقع (محاكاة)",
            "pipeline_debugger" to "مصحح أخطاء خط الأنابيب التفاعلي",
            "github_api_integration" to "تكامل واجهة برمجة تطبيقات جيت هاب",
            "github_api_desc" to "توصيل الإجراءات الحية لتشغيل تدفقات العمل",
            "pat_label" to "رمز الوصول الشخصي (التقليدي)",
            "repo_owner" to "مالك المستودع",
            "repo_name" to "اسم المستودع",
            "workflow_id_label" to "معرف سير العمل (اختياري)",
            "git_ref" to "فرع جيت",
            "test_connect" to "اختبار الاتصال",
            "trigger_dispatch" to "بدء التشغيل",
            "runs_history" to "سجل عمليات سير العمل الأخيرة",
            "gemini_assets" to "استوديو أصول جيميني",
            "gemini_assets_desc" to "تولد أيقونات تشغيل احترافية ورسومات ترحيبية",
            "visual_prompt" to "وصف المطالبة المرئية",
            "popular_suggestions" to "الاقتراحات الشائعة",
            "asset_type_target" to "نوع الأصل المستهدف",
            "target_aspect_ratio" to "نسبة العرض إلى الارتفاع المستهدفة",
            "studio_model" to "نموذج الاستوديو الاحترافي",
            "studio_model_desc" to "استخدام أوزان عالية الدقة (يتطلب حصة واجهة البرمجة)",
            "generate_native_asset" to "توليد الأصل الأصلي",
            "rendering_asset" to "جاري رندر أصل الاستوديو...",
            "generated_preview" to "معاينة الأصل المولد",
            "download_studio_asset" to "تنزيل أصل الاستوديو",
            "save_asset_toast" to "تم حفظ الأصل بنجاح في معرض الصور!",
            "awaiting_generation" to "في انتظار تعليمات التوليد البصري...",
            "rendering_pixels" to "تحليل المطالبات ورسم البكسلات...",
            "click_to_compile" to "انقر على 'بدء المحاكاة' لبدء التجميع",
            "step_install" to "تثبيت الاعتماديات",
            "step_typescript" to "فحص لغة TypeScript",
            "step_lint" to "فحص جودة الكود",
            "step_tests" to "اختبارات الوحدة",
            "step_build" to "بناء أندرويد الأصلي (Gradle)",
            "step_apk" to "توليد ملف APK والمخرجات"
        )
        val en = mapOf(
            "app_title" to "Expo Actions Console",
            "app_subtitle" to "GitHub CI/CD & Asset Builder",
            "tab_generator" to "Generator",
            "tab_terminal" to "Simulator",
            "tab_github" to "GitHub",
            "tab_assets" to "Assets",
            "workflow_params" to "Workflow Parameters",
            "project_name" to "Project Name",
            "node_version" to "Node.js Version",
            "expo_sdk" to "Expo SDK",
            "package_mgr" to "Package Manager",
            "target_artifact" to "Target Artifact",
            "config_cache" to "Configure Runner Cache",
            "config_cache_desc" to "Persist Gradle caches between workflow runs",
            "decode_keystore" to "Decode Android Keystore JKS",
            "decode_keystore_desc" to "Configure Base64 decoding and apksigner signing steps",
            "copy_code" to "Copy Code",
            "copied" to "Copied",
            "copied_toast" to "Copied YAML script to clipboard!",
            "runner_env_config" to "Runner Environment Configuration",
            "injected_failure" to "Injected Failure Simulation",
            "sim_speed" to "Simulation Speed Multiplier",
            "sim_speed_val" to "x Speed",
            "run_sim" to "Run Simulation",
            "reset" to "Reset",
            "runner_pipeline" to "Runner Pipeline Steps",
            "passed" to "Passed",
            "failed" to "Failed",
            "queued" to "Queued",
            "compilation_success" to "Compilation Succeeded Perfectly!",
            "compilation_success_desc" to "Your GitHub action executed without a single native compilation error. Released APK output metrics:",
            "run_id" to "Run Identifier",
            "artifact_title" to "Artifact Title",
            "apk_size" to "APK Package Size",
            "install_apk_sim" to "Install Signed APK (Simulated)",
            "pipeline_debugger" to "Interactive Pipeline Debugger",
            "github_api_integration" to "GitHub API Integration",
            "github_api_desc" to "Connect live actions to trigger dispatch workflows",
            "pat_label" to "Personal Access Token (classic)",
            "repo_owner" to "Repo Owner",
            "repo_name" to "Repo Name",
            "workflow_id_label" to "Workflow ID (Optional)",
            "git_ref" to "Git Ref",
            "test_connect" to "Test Connect",
            "trigger_dispatch" to "Trigger Dispatch",
            "runs_history" to "Recent Workflow Runs History",
            "gemini_assets" to "Gemini Asset Studio",
            "gemini_assets_desc" to "Generate professional launcher icons & splash illustrations",
            "visual_prompt" to "Visual Prompt Description",
            "popular_suggestions" to "Popular Suggestions",
            "asset_type_target" to "Asset Type Target",
            "target_aspect_ratio" to "Target Aspect Ratio",
            "studio_model" to "Professional Studio Model",
            "studio_model_desc" to "Use high-fidelity weights (requires API quota)",
            "generate_native_asset" to "Generate Native Asset",
            "rendering_asset" to "Rendering Studio Asset...",
            "generated_preview" to "Generated Asset Preview",
            "download_studio_asset" to "Download Studio Asset",
            "save_asset_toast" to "Saved asset successfully to Android gallery!",
            "awaiting_generation" to "Awaiting visual generation instructions...",
            "rendering_pixels" to "Parsing prompts and rendering pixels...",
            "click_to_compile" to "Click 'Run Simulation' to start compiling",
            "step_install" to "Install Dependencies",
            "step_typescript" to "TypeScript Check",
            "step_lint" to "Linter Checks",
            "step_tests" to "Unit Tests",
            "step_build" to "Android Native Build (Gradle)",
            "step_apk" to "APK & Artifact Generation"
        )
        return (if (lang == "ar") ar[key] else en[key]) ?: key
    }
}

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
    val language by viewModel.language.collectAsState()
    val scope = rememberCoroutineScope()

    val layoutDirection = if (language == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr
    CompositionLocalProvider(LocalLayoutDirection provides layoutDirection) {
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
                            Triple("generator", Loc.t("tab_generator", language), Icons.Default.Code),
                            Triple("terminal", Loc.t("tab_terminal", language), Icons.Default.Terminal),
                            Triple("github", Loc.t("tab_github", language), Icons.Default.Hub),
                            Triple("assets", Loc.t("tab_assets", language), Icons.Default.AutoAwesome)
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
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
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
                                text = Loc.t("app_title", language),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = Loc.t("app_subtitle", language),
                                color = Color.Gray,
                                fontSize = 11.sp
                            )
                        }
                    }

                    // Language Switcher Toggle
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(Color(0x1B, 0x19, 0x27), shape = RoundedCornerShape(8.dp))
                            .border(1.dp, Color(0x23, 0x1F, 0x33), shape = RoundedCornerShape(8.dp))
                            .padding(2.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (language == "en") MaterialTheme.colorScheme.primary else Color.Transparent)
                                .clickable { viewModel.setLanguage("en") }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "EN",
                                color = if (language == "en") Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (language == "ar") MaterialTheme.colorScheme.primary else Color.Transparent)
                                .clickable { viewModel.setLanguage("ar") }
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "AR",
                                color = if (language == "ar") Color.White else Color.Gray,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
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
}

// --- GENERATOR TAB COMPOSABLE ---
@Composable
fun GeneratorTab(viewModel: MainViewModel) {
    val language by viewModel.language.collectAsState()
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
                    text = Loc.t("workflow_params", language),
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))

                // Project Name input
                OutlinedTextField(
                    value = projectName,
                    onValueChange = { viewModel.projectName.value = it },
                    label = { Text(Loc.t("project_name", language)) },
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

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Node Version input
                    OutlinedTextField(
                        value = nodeVersion,
                        onValueChange = { viewModel.nodeVersion.value = it },
                        label = { Text(Loc.t("node_version", language)) },
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
                        label = { Text(Loc.t("expo_sdk", language)) },
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
                Text(Loc.t("package_mgr", language), color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
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
                Text(Loc.t("target_artifact", language), color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(Loc.t("config_cache", language), color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text(Loc.t("config_cache_desc", language), color = Color.Gray, fontSize = 10.sp)
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(Loc.t("decode_keystore", language), color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text(Loc.t("decode_keystore_desc", language), color = Color.Gray, fontSize = 10.sp)
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
                            Toast.makeText(context, Loc.t("copied_toast", language), Toast.LENGTH_SHORT).show()
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
                            Text(if (copied) Loc.t("copied", language) else Loc.t("copy", language), fontSize = 11.sp, fontWeight = FontWeight.Bold)
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
    val language by viewModel.language.collectAsState()
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
                    text = Loc.t("runner_env_config", language),
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(14.dp))

                // Error Selection dropdown/segmented button
                Text(Loc.t("injected_failure", language), color = Color.Gray, fontSize = 12.sp)
                Spacer(modifier = Modifier.height(6.dp))

                var expanded by remember { mutableStateOf(false) }
                val errorLabelsEn = mapOf(
                    "none" to "No Errors (Full Success Pass)",
                    "keystore" to "Apksigner Failure (Keystore Base64 Empty)",
                    "memory" to "OutOfMemoryError (Heap Crash)",
                    "jdk" to "JDK Version Mismatch (JVM Unsupported)"
                )
                val errorLabelsAr = mapOf(
                    "none" to "لا توجد أخطاء (نجاح كامل)",
                    "keystore" to "فشل apksigner (مستودع المفاتيح Base64 فارغ)",
                    "memory" to "خطأ نفاد الذاكرة OutOfMemoryError",
                    "jdk" to "عدم تطابق إصدار JDK (البيئة غير مدعومة)"
                )
                val errorLabels = if (language == "ar") errorLabelsAr else errorLabelsEn

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
                    Text(Loc.t("sim_speed", language), color = Color.White, fontSize = 13.sp)
                    Text("${speedFactor.toInt()}${Loc.t("sim_speed_val", language)}", color = MaterialTheme.colorScheme.primary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
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
                            Text(Loc.t("run_sim", language), fontSize = 13.sp, fontWeight = FontWeight.Bold)
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
                            Text(Loc.t("reset", language), fontSize = 13.sp)
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
                    text = Loc.t("runner_pipeline", language),
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

                    val stepNameKey = when (step.name) {
                        "Install Dependencies" -> "step_install"
                        "TypeScript Check" -> "step_typescript"
                        "Linter Checks" -> "step_lint"
                        "Unit Tests" -> "step_tests"
                        "Android Native Build (Gradle)" -> "step_build"
                        "APK & Artifact Generation" -> "step_apk"
                        else -> step.name
                    }
                    val displayStepName = if (stepNameKey.startsWith("step_")) Loc.t(stepNameKey, language) else step.name

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
                                text = displayStepName,
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
                            Text(Loc.t("passed", language), color = Color(0x22, 0xC5, 0x5E), fontSize = 12.sp)
                        } else if (isFailed) {
                            Text(Loc.t("failed", language), color = Color(0xEF, 0x44, 0x44), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        } else {
                            Text(Loc.t("queued", language), color = Color.DarkGray, fontSize = 12.sp)
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
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(Icons.Default.Terminal, contentDescription = "Console", tint = Color.DarkGray, modifier = Modifier.size(36.dp))
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(Loc.t("click_to_compile", language), color = Color.DarkGray, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
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
                        Text(Loc.t("compilation_success", language), color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(Loc.t("compilation_success_desc", language), color = Color.LightGray, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Column {
                            Text(Loc.t("run_id", language), color = Color.Gray, fontSize = 9.sp)
                            Text(data.runId, color = Color.White, fontSize = 11.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold)
                        }
                        Column {
                            Text(Loc.t("artifact_title", language), color = Color.Gray, fontSize = 9.sp)
                            Text(data.artifactName, color = Color.White, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
                        }
                        Column {
                            Text(Loc.t("apk_size", language), color = Color.Gray, fontSize = 9.sp)
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
                            Text(Loc.t("install_apk_sim", language), fontSize = 13.sp, fontWeight = FontWeight.Bold)
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
                        Text(Loc.t("pipeline_debugger", language), color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    val diagEn = when (errType) {
                        "jdk" -> Pair(
                            "JDK Out-of-Date Environment Error",
                            "The target Android Gradle Plugin compiled package requires JDK 21, but the runtime Ubuntu workspace was running JDK 11.\n\n✔️ Fix: Verify and replace your setup-java configuration block in workflow.yml to use java-version: '21' explicitly."
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

                    val diagAr = when (errType) {
                        "jdk" -> Pair(
                            "خطأ في إصدار JDK بيئة قديمة",
                            "تتطلب حزمة تجميع المكون الإضافي لـ Gradle إصدار JDK 21، ولكن بيئة العمل في Ubuntu كانت تقوم بتشغيل JDK 11.\n\n✔️ الإصلاح: تحقق من إعداد setup-java في ملف workflow.yml واستخدم java-version: '21'."
                        )
                        "memory" -> Pair(
                            "نفاد ذاكرة مجمع Gradle OOM",
                            "وصلت عملية daemon الخاصة بمجمع Android إلى الحد الأقصى من الذاكرة المخصصة وتم إنهاؤها بواسطة Linux OOM-killer.\n\n✔️ الإصلاح: قم بتحديث معاملات الذاكرة في gradle.properties باستخدام -Xmx3072m لمنح البيئة ذاكرة كافية."
                        )
                        "keystore" -> Pair(
                            "خطأ مصادقة توقيع مستودع المفاتيح Keystore",
                            "فشلت أداة apksigner برمز إرجاع غير صفري لأن سلسلة Base64 لفك تشفير مستودع المفاتيح كانت فارغة أو منتهية الصلاحية.\n\n✔️ الإصلاح: تأكد من حفظ سلسلة Base64 لمستودع المفاتيح JKS في GitHub Secrets وتعيين كلمات المرور بشكل صحيح."
                        )
                        else -> Pair("فشل بناء غير محدد", "لم يتم تجميع تفاصيل الفشل. تحقق من مخرجات الكونسول في الأعلى.")
                    }

                    val diagnostics = if (language == "ar") diagAr else diagEn

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
    val language by viewModel.language.collectAsState()
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
                    text = Loc.t("github_api_integration", language),
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = Loc.t("github_api_desc", language),
                    color = Color.Gray,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Token (masked)
                OutlinedTextField(
                    value = token,
                    onValueChange = { viewModel.githubToken.value = it },
                    label = { Text(Loc.t("pat_label", language)) },
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

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Owner
                    OutlinedTextField(
                        value = owner,
                        onValueChange = { viewModel.githubOwner.value = it },
                        label = { Text(Loc.t("repo_owner", language)) },
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
                        label = { Text(Loc.t("repo_name", language)) },
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

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Workflow ID
                    OutlinedTextField(
                        value = workflowId,
                        onValueChange = { viewModel.githubWorkflowId.value = it },
                        label = { Text(Loc.t("workflow_id_label", language)) },
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
                        label = { Text(Loc.t("git_ref", language)) },
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
                            .weight(1.5f)
                            .height(44.dp)
                            .testTag("btn_github_test")
                    ) {
                        if (loading && dispatchStatus == null) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                        } else {
                            Text(Loc.t("test_connect", language), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                            .weight(1.8f)
                            .height(44.dp)
                            .testTag("btn_github_dispatch")
                    ) {
                        if (loading && dispatchStatus != null) {
                            CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, modifier = Modifier.size(16.dp))
                        } else {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.Launch, "Dispatch", modifier = Modifier.size(14.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(Loc.t("trigger_dispatch", language), fontSize = 12.sp, fontWeight = FontWeight.Bold)
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
                        text = Loc.t("runs_history", language),
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
    val language by viewModel.language.collectAsState()
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
                        text = Loc.t("gemini_assets", language),
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = Loc.t("gemini_assets_desc", language),
                    color = Color.Gray,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Prompt Input
                OutlinedTextField(
                    value = prompt,
                    onValueChange = { viewModel.assetPrompt.value = it },
                    label = { Text(Loc.t("visual_prompt", language)) },
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
                Text(Loc.t("popular_suggestions", language), color = Color.Gray, fontSize = 11.sp)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val suggestionsEn = listOf(
                        "Dark Neon Hex Grid",
                        "Material Purple Terminal",
                        "Minimal Retro Gradient Icon"
                    )
                    val suggestionsAr = listOf(
                        "شبكة مسدسة نيون داكنة",
                        "طرفية بنفسجية مادية",
                        "أيقونة تدرج ريترو مبسطة"
                    )
                    val suggestions = if (language == "ar") suggestionsAr else suggestionsEn
                    suggestions.forEachIndexed { i, sug ->
                        Box(
                            modifier = Modifier
                                .background(Color(0x1B, 0x19, 0x27), shape = RoundedCornerShape(8.dp))
                                .border(1.dp, Color(0x23, 0x1F, 0x33), shape = RoundedCornerShape(8.dp))
                                .clickable { viewModel.assetPrompt.value = suggestionsEn[i] } // Keep English prompt value under the hood
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Text(sug, color = Color.LightGray, fontSize = 10.sp, fontWeight = FontWeight.Medium)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Asset Type Selector Row
                Text(Loc.t("asset_type_target", language), color = Color.Gray, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val typesEn = listOf(
                        Triple("icon", "App Icon", "1:1"),
                        Triple("splash_portrait", "Splash (Port)", "9:16"),
                        Triple("splash_landscape", "Splash (Land)", "16:9")
                    )
                    val typesAr = listOf(
                        Triple("icon", "أيقونة التطبيق", "1:1"),
                        Triple("splash_portrait", "شاشة ترحيب (رأسي)", "9:16"),
                        Triple("splash_landscape", "شاشة ترحيب (أفقي)", "16:9")
                    )
                    val types = if (language == "ar") typesAr else typesEn
                    types.forEachIndexed { idx, t ->
                        val selected = type == typesEn[idx].first
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(if (selected) MaterialTheme.colorScheme.primary else Color(0x1B, 0x19, 0x27))
                                .clickable { viewModel.assetType.value = typesEn[idx].first }
                                .border(
                                    width = 1.dp,
                                    color = if (selected) Color.Transparent else Color(0x23, 0x1F, 0x33),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .testTag("btn_asset_type_${typesEn[idx].first}"),
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
                        Text(Loc.t("target_aspect_ratio", language), color = Color.White, fontSize = 13.sp)
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(Loc.t("studio_model", language), color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                        Text(Loc.t("studio_model_desc", language), color = Color.Gray, fontSize = 10.sp)
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
                        Text(Loc.t("rendering_asset", language), fontSize = 13.sp)
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.AutoAwesome, "Sparkle", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(Loc.t("generate_native_asset", language), fontSize = 13.sp, fontWeight = FontWeight.Bold)
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
                    text = Loc.t("generated_preview", language),
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
                                Text(Loc.t("rendering_pixels", language), color = Color.Gray, fontSize = 11.sp, fontFamily = FontFamily.Monospace)
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
                                Text(Loc.t("awaiting_generation", language), color = Color.DarkGray, fontSize = 11.sp)
                            }
                        }
                    }
                }

                if (generatedImage != null && !isGenerating) {
                    Spacer(modifier = Modifier.height(14.dp))
                    Button(
                        onClick = {
                            Toast.makeText(context, Loc.t("save_asset_toast", language), Toast.LENGTH_SHORT).show()
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
                            Text(Loc.t("download_studio_asset", language), fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }
                }
            }
        }
    }
}

// --- Dynamic Animations Helpers ---
@Composable
fun Modifier.animateEnterExit(): Modifier = this // stub animation modifier
