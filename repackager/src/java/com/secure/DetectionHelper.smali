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

    .line 23
    sput v1, Lcom/secure/DetectionHelper;->accelX:F

    sput v1, Lcom/secure/DetectionHelper;->accelY:F

    sput v1, Lcom/secure/DetectionHelper;->accelZ:F

    .line 24
    const/4 v0, 0x0

    sput-boolean v0, Lcom/secure/DetectionHelper;->hasAccelData:Z

    .line 29
    sput v1, Lcom/secure/DetectionHelper;->lightValue:F

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;)V
    .locals 3
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    const/4 v2, 0x3

    .line 35
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 20
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

    .line 46
    const-string v0, "sensor"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/hardware/SensorManager;

    iput-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    .line 47
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    const/4 v1, 0x5

    invoke-virtual {v0, v1}, Landroid/hardware/SensorManager;->getDefaultSensor(I)Landroid/hardware/Sensor;

    move-result-object v0

    iput-object v0, p0, Lcom/secure/DetectionHelper;->light:Landroid/hardware/Sensor;

    .line 48
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->light:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 51
    const-string v0, "power"

    invoke-virtual {p1, v0}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/os/PowerManager;

    iput-object v0, p0, Lcom/secure/DetectionHelper;->powerManager:Landroid/os/PowerManager;

    .line 52
    invoke-virtual {p0}, Lcom/secure/DetectionHelper;->isScreenDisplayOn()V

    .line 53
    return-void
.end method


# virtual methods
.method public isMachineAccess()Z
    .locals 3

    .prologue
    const/4 v2, 0x0

    .line 128
    iget-boolean v1, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    if-nez v1, :cond_0

    .line 129
    const/4 v0, 0x1

    .line 137
    .local v0, "machineAccess":Z
    :goto_0
    return v0

    .line 130
    .end local v0    # "machineAccess":Z
    :cond_0
    sget-boolean v1, Lcom/secure/DetectionHelper;->hasAccelData:Z

    if-eqz v1, :cond_1

    sget v1, Lcom/secure/DetectionHelper;->accelX:F

    cmpl-float v1, v1, v2

    if-nez v1, :cond_1

    sget v1, Lcom/secure/DetectionHelper;->accelY:F

    cmpl-float v1, v1, v2

    if-nez v1, :cond_1

    sget v1, Lcom/secure/DetectionHelper;->accelZ:F

    cmpl-float v1, v1, v2

    if-nez v1, :cond_1

    .line 131
    const/4 v0, 0x1

    .restart local v0    # "machineAccess":Z
    goto :goto_0

    .line 132
    .end local v0    # "machineAccess":Z
    :cond_1
    sget v1, Lcom/secure/DetectionHelper;->lightValue:F

    cmpl-float v1, v1, v2

    if-nez v1, :cond_2

    .line 133
    const/4 v0, 0x1

    .restart local v0    # "machineAccess":Z
    goto :goto_0

    .line 135
    .end local v0    # "machineAccess":Z
    :cond_2
    const/4 v0, 0x0

    .restart local v0    # "machineAccess":Z
    goto :goto_0
.end method

.method public isScreenDisplayOn()V
    .locals 2

    .prologue
    .line 58
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x14

    if-lt v0, v1, :cond_1

    .line 59
    iget-object v0, p0, Lcom/secure/DetectionHelper;->powerManager:Landroid/os/PowerManager;

    invoke-virtual {v0}, Landroid/os/PowerManager;->isInteractive()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 60
    const/4 v0, 0x1

    iput-boolean v0, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    .line 67
    :goto_0
    return-void

    .line 62
    :cond_0
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/secure/DetectionHelper;->screenInteractive:Z

    goto :goto_0

    .line 65
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
    .line 121
    return-void
.end method

.method protected onPause()V
    .locals 1

    .prologue
    .line 80
    iget-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    invoke-virtual {v0, p0}, Landroid/hardware/SensorManager;->unregisterListener(Landroid/hardware/SensorEventListener;)V

    .line 81
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    invoke-virtual {v0, p0}, Landroid/hardware/SensorManager;->unregisterListener(Landroid/hardware/SensorEventListener;)V

    .line 82
    return-void
.end method

.method public onResume()V
    .locals 3

    .prologue
    const/4 v2, 0x3

    .line 71
    iget-object v0, p0, Lcom/secure/DetectionHelper;->accelManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->accelerometer:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 74
    iget-object v0, p0, Lcom/secure/DetectionHelper;->lightManager:Landroid/hardware/SensorManager;

    iget-object v1, p0, Lcom/secure/DetectionHelper;->light:Landroid/hardware/Sensor;

    invoke-virtual {v0, p0, v1, v2}, Landroid/hardware/SensorManager;->registerListener(Landroid/hardware/SensorEventListener;Landroid/hardware/Sensor;I)Z

    .line 77
    return-void
.end method

.method public onSensorChanged(Landroid/hardware/SensorEvent;)V
    .locals 11
    .param p1, "event"    # Landroid/hardware/SensorEvent;

    .prologue
    const/4 v10, 0x0

    const v9, 0x3d23d70a    # 0.04f

    const/4 v8, 0x1

    .line 87
    iget-object v6, p1, Landroid/hardware/SensorEvent;->sensor:Landroid/hardware/Sensor;

    invoke-virtual {v6}, Landroid/hardware/Sensor;->getType()I

    move-result v6

    if-ne v6, v8, :cond_5

    .line 88
    sput-boolean v8, Lcom/secure/DetectionHelper;->hasAccelData:Z

    .line 89
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    aget v3, v6, v10

    .line 90
    .local v3, "x":F
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    aget v4, v6, v8

    .line 91
    .local v4, "y":F
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    const/4 v7, 0x2

    aget v5, v6, v7

    .line 92
    .local v5, "z":F
    iget-boolean v6, p0, Lcom/secure/DetectionHelper;->mInitialized:Z

    if-nez v6, :cond_1

    .line 93
    iput v3, p0, Lcom/secure/DetectionHelper;->mLastX:F

    .line 94
    iput v4, p0, Lcom/secure/DetectionHelper;->mLastY:F

    .line 95
    iput v5, p0, Lcom/secure/DetectionHelper;->mLastZ:F

    .line 96
    iput-boolean v8, p0, Lcom/secure/DetectionHelper;->mInitialized:Z

    .line 116
    .end local v3    # "x":F
    .end local v4    # "y":F
    .end local v5    # "z":F
    :cond_0
    :goto_0
    return-void

    .line 98
    .restart local v3    # "x":F
    .restart local v4    # "y":F
    .restart local v5    # "z":F
    :cond_1
    iget v6, p0, Lcom/secure/DetectionHelper;->mLastX:F

    sub-float/2addr v6, v3

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v0

    .line 99
    .local v0, "deltaX":F
    iget v6, p0, Lcom/secure/DetectionHelper;->mLastY:F

    sub-float/2addr v6, v4

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v1

    .line 100
    .local v1, "deltaY":F
    iget v6, p0, Lcom/secure/DetectionHelper;->mLastZ:F

    sub-float/2addr v6, v5

    invoke-static {v6}, Ljava/lang/Math;->abs(F)F

    move-result v2

    .line 101
    .local v2, "deltaZ":F
    cmpg-float v6, v0, v9

    if-gez v6, :cond_2

    const/4 v0, 0x0

    .line 102
    :cond_2
    cmpg-float v6, v1, v9

    if-gez v6, :cond_3

    const/4 v1, 0x0

    .line 103
    :cond_3
    cmpg-float v6, v2, v9

    if-gez v6, :cond_4

    const/4 v2, 0x0

    .line 104
    :cond_4
    iput v3, p0, Lcom/secure/DetectionHelper;->mLastX:F

    .line 105
    iput v4, p0, Lcom/secure/DetectionHelper;->mLastY:F

    .line 106
    iput v5, p0, Lcom/secure/DetectionHelper;->mLastZ:F

    .line 108
    sput v0, Lcom/secure/DetectionHelper;->accelX:F

    .line 109
    sput v1, Lcom/secure/DetectionHelper;->accelY:F

    .line 110
    sput v2, Lcom/secure/DetectionHelper;->accelZ:F

    goto :goto_0

    .line 113
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

    .line 114
    iget-object v6, p1, Landroid/hardware/SensorEvent;->values:[F

    aget v6, v6, v10

    sput v6, Lcom/secure/DetectionHelper;->lightValue:F

    goto :goto_0
.end method

