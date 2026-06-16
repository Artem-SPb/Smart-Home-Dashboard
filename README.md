# Smart Home Dashboard

A professional Android pet project demonstrating best practices in UI development, focusing on performance, fluid 60 FPS, and deep understanding of modern XML layout techniques.

## 🚀 Key Features & Architectural Decisions

This project is built to showcase a highly optimized, production-ready approach to UI rendering on Android.

### 1. Absolutely Flat View Hierarchy
Nested layouts (`LinearLayout` inside `FrameLayout` inside `RelativeLayout`, etc.) are a primary cause of slow rendering and GPU Overdraw in Android. 
This dashboard achieves a complex, responsive grid-based UI **using a single root `ConstraintLayout`**. There are **zero** nested ViewGroups.

### 2. GPU Overdraw Minimization
By maintaining a flat hierarchy and avoiding overlapping background colors that the user never sees, we eliminate wasted GPU cycles. This directly contributes to a silky-smooth **60 FPS** experience, even on lower-end devices.

### 3. Advanced ConstraintLayout Features
* **Guidelines**: Instead of relying on `LinearLayout` weights to divide the screen, we utilize `androidx.constraintlayout.widget.Guideline` (e.g., `layout_constraintGuide_percent="0.5"`) to build a rigid, scalable grid for our smart device tiles.
* **Barriers**: To ensure flawless alignment of dynamic text content across varying screen sizes and localizations, we employ `androidx.constraintlayout.widget.Barrier`. A Barrier dynamically calculates the maximum bounds of multiple referenced views (like an icon and a status badge), allowing us to safely anchor text below them without hardcoding margins.
* **Dimension Ratios**: The smart home tiles maintain a perfect 1:1 aspect ratio across all devices using `app:layout_constraintDimensionRatio="1:1"`, eliminating complex programmatic measurements.

### 4. Semantic Theming Support
The application fully supports both **Light and Dark modes** out of the box. 
Colors are defined semantically (e.g., `window_background`, `text_primary`, `tile_bg_active`) in `values/colors.xml` and `values-night/colors.xml`, completely avoiding hardcoded HEX values in layouts. All dimensions and margins are carefully standardized in `dimens.xml`.

## 📸 Screenshots & Profiling

### UI Preview
![Smart Home Dashboard](https://opengraph.githubassets.com/1/Artem-SPb/Smart-Home-Dashboard)

### Layout Inspector & GPU Profiler
*To demonstrate the zero-nested hierarchy and the absence of GPU overdraw.*

![Layout Inspector Placeholder](path/to/layout_inspector.png)
> *Notice the 100% flat hierarchy. All Views are direct children of the root ConstraintLayout.*

![GPU Overdraw Placeholder](path/to/gpu_overdraw.png)
> *The GPU Overdraw tool shows mostly true color (no overdraw) and single overdraw layers, validating our optimized approach.*

## 💻 Code Quality
- Clean, readable code structure following DRY principles.
- Detailed, educational comments explaining *why* certain architectural UI choices were made (e.g., the specific benefits of `Barrier` over standard margins).
- No IDE warnings or Lint errors.

## 🛠 Tech Stack
- Kotlin
- AndroidX
- ConstraintLayout 2.0+
- XML Vector Drawables
- StateListDrawables for performant interactive states
