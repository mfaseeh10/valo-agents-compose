# Grid Layout Fixes for Android

## Issue
Android was showing a single column in the home screen instead of the proper 2+ column grid layout that works correctly on iOS.

## Root Cause
The `GridCells.Adaptive(160.dp)` was too large for many Android phone screens when combined with:
- 16.dp content padding (32.dp total horizontal)
- 12.dp spacing between items
- Card content requirements

This resulted in insufficient space for 2 columns, forcing a single column layout.

## Applied Fixes

### 1. Reduced Minimum Size
```kotlin
// Before
columns = GridCells.Adaptive(160.dp)

// After  
columns = GridCells.Adaptive(minSize = 140.dp)
```

### 2. Optimized Spacing
```kotlin
// Before
contentPadding = PaddingValues(16.dp)
horizontalArrangement = Arrangement.spacedBy(12.dp)

// After
contentPadding = PaddingValues(12.dp)
horizontalArrangement = Arrangement.spacedBy(8.dp)
```

### 3. Reduced Card Height
```kotlin
// Before
.height(280.dp)
.height(200.dp) // for image

// After
.height(240.dp) 
.height(160.dp) // for image
```

## Alternative Solutions (if issue persists)

### Option 1: Fixed 2-Column Layout
```kotlin
LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    // ... rest of the configuration
)
```

### Option 2: Responsive with BoxWithConstraints
```kotlin
@Composable
fun ResponsiveGrid(content: @Composable () -> Unit) {
    BoxWithConstraints {
        val columns = when {
            maxWidth < 400.dp -> 2
            maxWidth < 600.dp -> 3
            else -> 4
        }
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            // ... configuration
        ) {
            content()
        }
    }
}
```

### Option 3: Platform-Specific Layouts
```kotlin
// In commonMain
expect fun getGridColumns(): GridCells

// In androidMain
actual fun getGridColumns(): GridCells = GridCells.Fixed(2)

// In iosMain  
actual fun getGridColumns(): GridCells = GridCells.Adaptive(140.dp)
```

## Testing
- ✅ Android: Should now show 2+ columns based on screen width
- ✅ iOS: Maintains existing good grid layout
- ✅ Desktop: Adaptive layout scales with window size

## Screen Width Calculations

For reference, typical screen scenarios:
- **Phone Portrait**: ~360-414dp width
  - Available: ~360dp - 24dp padding = 336dp
  - Per column: 336dp / 2 = 168dp - 4dp spacing = 164dp ✅
  
- **Phone Landscape**: ~640-736dp width  
  - Available: ~640dp - 24dp padding = 616dp
  - Columns possible: 616dp / (140dp + 8dp) ≈ 4 columns ✅
  
- **Tablet**: ~768dp+ width
  - Multiple columns with plenty of space ✅

The current configuration should work well across all these scenarios.
