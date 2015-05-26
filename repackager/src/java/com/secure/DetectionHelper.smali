.class public Lcom/secure/DetectionHelper;
.super Ljava/lang/Object;
.source "DetectionHelper.java"

# interfaces
.implements Landroid/hardware/SensorEventListener;


# static fields
.field private static accelX:F

.field private static accelY:F

.field private static accelZ:F

.field private static hasAccelData:Z

.field private static lightValue:F


# instance fields
.field private final NOISE:F

.field private accelManager:Landroid/hardware/SensorManager;

.field private accelerometer:Landroid/hardware/Sensor;

.field private light:Landroid/hardware/Sensor;

.field private lightManager:Landroid/hardware/SensorManager;

.field private mInitialized:Z

.field private mLastX:F

.field private mLastY:F

.field private mLastZ:F

.field private powerManager:Landroid/os/PowerManager;

.field private screenInteractive:Z


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    const/4 v1, 0x0

    .line 28
    sput v1, Lcom/secure/DetectionHelper;->accelX:F

    sput v1, Lcom/secure/DetectionHelper;->accelY:F

    sput v1, Lcom/secure/DetectionHelper;->accelZ:F

    .line 29
    const/4 v0, 0x0

    sput-boolean v0, Lcom/secure/DetectionHelper;->hasAccelData:Z

    .line 33
    sput v1, Lcom/secure/DetectionHelper;->lightValue:F

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v2, 0x3

    .line 38
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 25
    const v0, 0x3d23d70a    # 0.04f

    iput v0, p0, Lcom/secure/DetectionHelper;->NOISE:F

    .line 40
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/secure/DetectionHelper;->mInitialized:Z

    .line 41
    const-string v0, "sensor"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/hardware/SensorManager;

    iput-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    .line 42
    iget-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/hardware/SensorManager;->getDefaultSensor(I)Landroid/hardware/Sensor;

    move-result-object v0

    iput-object v0, p0, Lcom/secure/DetectionHelper;->accelerometer:Landroid/hardware/Sensor;

    .line 43
    iget-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->accelerometer:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 45
    const-string v0, "sensor"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/hardware/SensorManager;

    iput-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    .line 46
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/hardware/SensorManager;->getDefaultSensor(I)Landroid/hardware/Sensor;

    move-result-object v0

    iput-object v0, p0, Lcom/secure/DetectionHelper;->light:Landroid/hardware/Sensor;

    .line 47
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->light:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 49
    const-string v0, "power"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/PowerManager;

    iput-object v0, p0, Lcom/secure/DetectionHelper;->powerManager:Landroid/os/PowerManager;

    .line 50
    invoke-virtual {p0}, Lcom/secure/DetectionHelper;->isScreenDisplayOn()V

    .line 51
    return-void
.end method


# virtual methods
.method public isMachineAccess()Z
    .locals 9

    .prologue
    const/4 v8, 0x0

    .line 135
    iget-boolean v7, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    if-nez v7, :cond_1

    .line 136
    const/4 v5, 0x1

    .line 147
    .local v5, "machineAccess":Z
    :goto_0
    const/4 v2, 0x0

    .line 149
    .local v2, "foundMethod":Z
    invoke-static {}, Ljava/lang/Thread;->currentThread()Ljava/lang/Thread;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/Thread;->getStackTrace()[Ljava/lang/StackTraceElement;

    move-result-object v0

    .local v0, "arr$":[Ljava/lang/StackTraceElement;
    array-length v4, v0

    .local v4, "len$":I
    const/4 v3, 0x0

    .local v3, "i$":I
    :goto_1
    if-ge v3, v4, :cond_5

    aget-object v1, v0, v3

    .line 150
    .local v1, "e":Ljava/lang/StackTraceElement;
    invoke-virtual {v1}, Ljava/lang/StackTraceElement;->toString()Ljava/lang/String;

    move-result-object v6

    .line 151
    .local v6, "stackTrace":Ljava/lang/String;
    const-string v7, "android.view.View$PerformClick.run"

    invoke-virtual {v6, v7}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v7

    if-eqz v7, :cond_4

    .line 152
    const/4 v5, 0x0

    .line 153
    const/4 v2, 0x1

    .line 149
    :cond_0
    :goto_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_1

    .line 137
    .end local v0    # "arr$":[Ljava/lang/StackTraceElement;
    .end local v1    # "e":Ljava/lang/StackTraceElement;
    .end local v2    # "foundMethod":Z
    .end local v3    # "i$":I
    .end local v4    # "len$":I
    .end local v5    # "machineAccess":Z
    .end local v6    # "stackTrace":Ljava/lang/String;
    :cond_1
    sget-boolean v7, Lcom/secure/DetectionHelper;->hasAccelData:Z

    if-eqz v7, :cond_2

    sget v7, Lcom/secure/DetectionHelper;->accelX:F

    cmpl-float v7, v7, v8

    if-nez v7, :cond_2

    sget v7, Lcom/secure/DetectionHelper;->accelY:F

    cmpl-float v7, v7, v8

    if-nez v7, :cond_2

    sget v7, Lcom/secure/DetectionHelper;->accelZ:F

    cmpl-float v7, v7, v8

    if-nez v7, :cond_2

    .line 138
    const/4 v5, 0x1

    .restart local v5    # "machineAccess":Z
    goto :goto_0

    .line 139
    .end local v5    # "machineAccess":Z
    :cond_2
    sget v7, Lcom/secure/DetectionHelper;->lightValue:F

    cmpl-float v7, v7, v8

    if-nez v7, :cond_3

    .line 140
    const/4 v5, 0x1

    .restart local v5    # "machineAccess":Z
    goto :goto_0

    .line 142
    .end local v5    # "machineAccess":Z
    :cond_3
    const/4 v5, 0x0

    .restart local v5    # "machineAccess":Z
    goto :goto_0

    .line 154
    .restart local v0    # "arr$":[Ljava/lang/StackTraceElement;
    .restart local v1    # "e":Ljava/lang/StackTraceElement;
    .restart local v2    # "foundMethod":Z
    .restart local v3    # "i$":I
    .restart local v4    # "len$":I
    .restart local v6    # "stackTrace":Ljava/lang/String;
    :cond_4
    const-string v7, "android.support.v4.widget.SwipeRefreshLayout$1.onAnimationEnd"

    invoke-virtual {v6, v7}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v7

    if-eqz v7, :cond_0

    .line 155
    const/4 v5, 0x0

    .line 156
    const/4 v2, 0x1

    goto :goto_2

    .line 160
    .end local v1    # "e":Ljava/lang/StackTraceElement;
    .end local v6    # "stackTrace":Ljava/lang/String;
    :cond_5
    if-nez v2, :cond_6

    .line 161
    const/4 v5, 0x1

    .line 164
    :cond_6
    return v5
.end method

.method public isScreenDisplayOn()V
    .locals 2

    .prologue
    .line 57
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x14

    if-lt v0, v1, :cond_1

    .line 58
    iget-object v0, p0, Lcom/secure/DetectionHelper;->powerManager:Landroid/os/PowerManager;

    invoke-virtual {v0}, Landroid/os/PowerManager;->isInteractive()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 59
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    .line 66
    :goto_0
    return-void

    .line 61
    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    goto :goto_0

    .line 64
    :cond_1
    iget-object v0, p0, Lcom/secure/DetectionHelper;->powerManager:Landroid/os/PowerManager;

    invoke-virtual {v0}, Landroid/os/PowerManager;->isScreenOn()Z

    move-result v0

    iput-boolean v0, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    goto :goto_0
.end method

.method public onAccuracyChanged(Landroid/hardware/Sensor;I)V
    .locals 0
    .param p1, "sensor"    # Landroid/hardware/Sensor;
    .param p2, "accuracy"    # I

    .prologue
    .line 126
    return-void
.end method

.method protected onPause()V
    .locals 1

    .prologue
    .line 79
    iget-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    invoke-virtual {v0, p0}, Landroid/hardware/SensorManager;->unregisterListener(Landroid/hardware/SensorEventListener;)V

    .line 80
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    invoke-virtual {v0, p0}, Landroid/hardware/SensorManager;->unregisterListener(Landroid/hardware/SensorEventListener;)V

    .line 81
    return-void
.end method

.method public onResume()V
    .locals 3

    .prologue
    const/4 v2, 0x3

    .line 70
    iget-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->accelerometer:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 73
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->light:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 76
    return-void
.end method

.method public onSensorChanged(Landroid/hardware/SensorEvent;)V
    .locals 11
    .param p1, "event"    # Landroid/hardware/SensorEvent;

    .prologue
    const/4 v10, 0x0

    const v9, 0x3d23d70a    # 0.04f

    const/4 v8, 0x1

    .line 88
    iget-object v6, p1, Landroid/hardware/SensorEvent;->sensor:Landroid/hardware/Sensor;

    invoke-virtual {v6}, Landroid/hardware/Sensor;->getType()I

    move-result v6

    if-ne v6, v8, :cond_5

    .line 89
    sput-boolean v8, Lcom/secure/DetectionHelper;->hasAccelData:Z

    .line 90
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    aget v3, v6, v10

    .line 91
    .local v3, "x":F
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    aget v4, v6, v8

    .line 92
    .local v4, "y":F
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    const/4 v7, 0x2

    aget v5, v6, v7

    .line 93
    .local v5, "z":F
    iget-boolean v6, p0, Lcom/secure/DetectionHelper;->mInitialized:Z

    if-nez v6, :cond_1

    .line 94
    iput v3, p0, Lcom/secure/DetectionHelper;->mLastX:F

    .line 95
    iput v4, p0, Lcom/secure/DetectionHelper;->mLastY:F

    .line 96
    iput v5, p0, Lcom/secure/DetectionHelper;->mLastZ:F

    .line 97
    iput-boolean v8, p0, Lcom/secure/DetectionHelper;->mInitialized:Z

    .line 117
    .end local v3    # "x":F
    .end local v4    # "y":F
    .end local v5    # "z":F
    :cond_0
    :goto_0
    return-void

    .line 99
    .restart local v3    # "x":F
    .restart local v4    # "y":F
    .restart local v5    # "z":F
    :cond_1
    iget v6, p0, Lcom/secure/DetectionHelper;->mLastX:F

    sub-float/2addr v6, v3

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v0

    .line 100
    .local v0, "deltaX":F
    iget v6, p0, Lcom/secure/DetectionHelper;->mLastY:F

    sub-float/2addr v6, v4

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v1

    .line 101
    .local v1, "deltaY":F
    iget v6, p0, Lcom/secure/DetectionHelper;->mLastZ:F

    sub-float/2addr v6, v5

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v2

    .line 102
    .local v2, "deltaZ":F
    cmpg-float v6, v0, v9

    if-gez v6, :cond_2

    const/4 v0, 0x0

    .line 103
    :cond_2
    cmpg-float v6, v1, v9

    if-gez v6, :cond_3

    const/4 v1, 0x0

    .line 104
    :cond_3
    cmpg-float v6, v2, v9

    if-gez v6, :cond_4

    const/4 v2, 0x0

    .line 105
    :cond_4
    iput v3, p0, Lcom/secure/DetectionHelper;->mLastX:F

    .line 106
    iput v4, p0, Lcom/secure/DetectionHelper;->mLastY:F

    .line 107
    iput v5, p0, Lcom/secure/DetectionHelper;->mLastZ:F

    .line 109
    sput v0, Lcom/secure/DetectionHelper;->accelX:F

    .line 110
    sput v1, Lcom/secure/DetectionHelper;->accelY:F

    .line 111
    sput v2, Lcom/secure/DetectionHelper;->accelZ:F

    goto :goto_0

    .line 114
    .end local v0    # "deltaX":F
    .end local v1    # "deltaY":F
    .end local v2    # "deltaZ":F
    .end local v3    # "x":F
    .end local v4    # "y":F
    .end local v5    # "z":F
    :cond_5
    iget-object v6, p1, Landroid/hardware/SensorEvent;->sensor:Landroid/hardware/Sensor;

    invoke-virtual {v6}, Landroid/hardware/Sensor;->getType()I

    move-result v6

    const/4 v7, 0x5

    if-ne v6, v7, :cond_0

    .line 115
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    aget v6, v6, v10

    sput v6, Lcom/secure/DetectionHelper;->lightValue:F

    goto :goto_0
.end method

