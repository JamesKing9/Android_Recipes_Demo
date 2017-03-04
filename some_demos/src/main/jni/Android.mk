LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

#编译打包后的模块名字，也就是so类库的名字
LOCAL_MODULE    := hellojni
#指定要编译打包的C文件，C文件会被打包成so类库
LOCAL_SRC_FILES := hellojni.c
LOCAL_LDLIBS := -llog -landroid -lEGL -lGLESv1_CM
LOCAL_STATIC_LIBRARIES := android_native_app_glue

include $(BUILD_SHARED_LIBRARY)

$(call import-module,android/native_app_glue)