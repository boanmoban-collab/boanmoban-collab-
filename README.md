<div align="center">
<img width="1200" height="475" alt="GHBanner" src="https://ai.google.dev/static/site-assets/images/share-ais-513315318.png" />
</div>

# Expo Actions Console

A modern native Android application built with Kotlin and Jetpack Compose to generate production-ready GitHub Actions workflows for Expo Android builds, simulate and debug native compilation step-by-step, and generate studio-quality app assets using Gemini.

## Core Features

-   **Expo YAML Workflow Generator**: Dynamically configure and generate robust, cached `.yml` build configurations optimized for Android compilation.
-   **Step-by-Step Native Build Simulator**: Run native Android Gradle compilation simulations, injecting custom errors (Heap Space Out-Of-Memory, JDK version mismatch, or Signing errors) to learn and test diagnostics.
-   **GitHub API Dispatch Center**: Configure Personal Access Tokens, test repo connections, trigger manual workflow dispatches, and track recent runs' histories directly.
-   **Gemini Asset Studio**: Describe launcher icons or splash layouts, select resolutions, and leverage Gemini image generation models to render custom assets instantly. Includes an offline procedural vector graphics fallback engine.

## Prerequisites & Secrets

1. Configure your **`GEMINI_API_KEY`** in the **Secrets panel in AI Studio** to enable the live image generation capabilities in the Asset Studio.
2. The project uses **JDK 21** and **Android SDK 35** configured via modern Gradle Kotlin DSL.

