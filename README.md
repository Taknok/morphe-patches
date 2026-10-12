# 👋🧩 Morphe Patches template

Taknok repository for Morphe Patches.

## ❓ About

Patches for apps I like.

### How to use these patches

Click here to add these patches to Morphe: https://morphe.software/add-source?github=Taknok/morphe-patches

## 🩹 Patches list

<!-- PATCHES_START EXPANDED -->
> **[v1.0.0-dev.1](https://github.com/Taknok/morphe-patches/releases/tag/v1.0.0-dev.1)**&nbsp;&nbsp;•&nbsp;&nbsp;`dev`&nbsp;&nbsp;•&nbsp;&nbsp;1 patches total
<details open>
<summary>📦 Phone&nbsp;&nbsp;•&nbsp;&nbsp;1 patch</summary>
<br>

**🎯 Supported versions:**

| 241.0.991235506-pixel |
| :---: |

| 💊&nbsp;Patch | 📜&nbsp;Description | ⚙️&nbsp;Options |
|----------|----------------|-----------|
| [Enable call recording](#enable-call-recording) | Enables the in-call recording action when the device recorder capability is unavailable. |  |

</details>

<!-- PATCHES_END -->

### 🛠️ Building locally

- Run `./gradlew buildAndroid`
- The built patches .mpp file is found in `patches/build/libs/patches-*.mpp`
- Patch the mpp file using [Morphe-Desktop](https://github.com/MorpheApp/morphe-desktop)
  like any other patch bundle.

#### VS Code Dev Container

Open the repository in VS Code and choose **Dev Containers: Reopen in Container**. The
container includes Java 21 and the Android SDK needed to run `./gradlew buildAndroid`.
If the build needs access to Morphe's private package registry, set `GITHUB_ACTOR` and
`GITHUB_TOKEN` in your local environment before opening the container.

See the [Morphe documentation](https://github.com/MorpheApp/morphe-documentation) for more information.

## 📜 License

Taknok Patches are licensed under the [GNU General Public License v3.0](LICENSE)
