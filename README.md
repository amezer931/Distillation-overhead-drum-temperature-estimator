# Distillation Column Overhead Temperature Estimator

**Developer: Process engineer Ahmed Mezer**

Android app that estimates the overhead drum temperature of a distillation column
from the overhead component flows.

## Download

**[Download ColumnOverheadTemp.apk (latest version)](../../releases/latest/download/ColumnOverheadTemp.apk)**

Open the file on the phone and allow installation from unknown sources.
Works offline on Android 5.0 and newer. All versions are on the [releases page](../../releases).

## What it does

- 26 pure components from C1 to C7 (paraffins, iso-paraffins, naphthenes, benzene)
  from a built-in database, and petroleum cuts.
- Composition as flow rates (volume or molar), vol.% or mol.%; switching converts the entered values.
- Cuts from density and ASTM D86: VABP and MeABP by API TDB Procedure 2B1.1,
  molecular weight by API TDB equation 2B2.1-1.
- Critical properties by Riazi-Daubert, acentric factor by Lee-Kesler, K-values by Wilson.
- Drum temperature where Σ fi·Ki = Σ fi, interpolated between two trial temperatures.
- "Steam used" option: with steam, P_HC = P_overhead − water vapour pressure.
- Selectable units: temperature, pressure, density (API, SG, kg/m³, lb/ft³) and
  volume flow (BPSD, m³/hr, ft³/hr).
- Step-by-step solution with every equation.
- Export of the inputs and the step-by-step calculation as a PDF report
  (button "Export PDF report", then choose "Save as PDF").

## Build

`./gradlew assembleDebug`, or open the folder in Android Studio and use Build > Build APK(s).
Every push to `main` builds the APK with GitHub Actions and attaches it to the release.

The calculator itself is `app/src/main/assets/index.html`.
