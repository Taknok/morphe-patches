FROM mcr.microsoft.com/devcontainers/java:1-21-bookworm

ARG ANDROID_CMDLINE_TOOLS_VERSION=15859902

ENV ANDROID_HOME=/opt/android-sdk
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH="${ANDROID_SDK_ROOT}/cmdline-tools/latest/bin:${ANDROID_SDK_ROOT}/platform-tools:${PATH}"

RUN mkdir -p "${ANDROID_SDK_ROOT}/cmdline-tools"
RUN curl -fsSL "https://dl.google.com/android/repository/commandlinetools-linux-${ANDROID_CMDLINE_TOOLS_VERSION}_latest.zip" -o /tmp/android-command-line-tools.zip
RUN unzip -q /tmp/android-command-line-tools.zip -d /tmp/android-command-line-tools
RUN  mv /tmp/android-command-line-tools/cmdline-tools "${ANDROID_SDK_ROOT}/cmdline-tools/latest"
RUN  rm -rf /tmp/android-command-line-tools /tmp/android-command-line-tools.zip
RUN  yes | sdkmanager --sdk_root="${ANDROID_SDK_ROOT}" --licenses >/dev/null
RUN sdkmanager --sdk_root="${ANDROID_SDK_ROOT}" \
        "platform-tools" \
        "platforms;android-35" \
        "build-tools;35.0.0"
RUN  chown -R vscode:vscode "${ANDROID_SDK_ROOT}"
