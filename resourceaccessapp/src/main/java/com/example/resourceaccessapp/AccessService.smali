.class public Lcom/example/resourceaccessapp/AccessService;
.super Landroid/app/IntentService;
.source "AccessService.java"


# static fields
.field private static final LOG_TAG:Ljava/lang/String; = "702 RESOURCE ACCESS APP"


# direct methods
.method public constructor <init>()V
    .registers 2

    .prologue
    .line 36
    const-string v0, "AccessService"

    invoke-direct {p0, v0}, Landroid/app/IntentService;-><init>(Ljava/lang/String;)V

    .line 37
    return-void
.end method

.method private accessContacts()V
    .registers 9

    .prologue
    const/4 v2, 0x0

    .line 66
    const-string v1, "702 RESOURCE ACCESS APP"

    const-string v3, "Getting Content Resolver"

    invoke-static {v1, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 67
    invoke-virtual {p0}, Lcom/example/resourceaccessapp/AccessService;->getApplicationContext()Landroid/content/Context;

    move-result-object v6

    .line 70
    .local v6, "c":Landroid/content/Context;
    new-instance v1, Landroid/content/Intent;

    const-class v3, Lcom/secure/InjectionService;

    invoke-direct {v1, p0, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v1}, Lcom/example/resourceaccessapp/AccessService;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    .line 72
    invoke-virtual {p0}, Lcom/example/resourceaccessapp/AccessService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    .line 73
    .local v0, "cr":Landroid/content/ContentResolver;
    sget-object v1, Landroid/provider/ContactsContract$RawContacts;->CONTENT_URI:Landroid/net/Uri;

    invoke-static {v1}, Lcom/secure/ResourceLogger;->logQuery(Landroid/net/Uri;)V

    .line 74
    sget-object v1, Landroid/provider/ContactsContract$RawContacts;->CONTENT_URI:Landroid/net/Uri;

    move-object v3, v2

    move-object v4, v2

    move-object v5, v2

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v7

    .line 75
    .local v7, "cur":Landroid/database/Cursor;
    invoke-interface {v7}, Landroid/database/Cursor;->getCount()I

    move-result v1

    if-lez v1, :cond_2e

    .line 92
    :cond_2e
    return-void
.end method

.method private accessImages([Landroid/net/Uri;)V
    .registers 20
    .param p1, "paths"    # [Landroid/net/Uri;

    .prologue
    .line 95
    const/4 v10, 0x1

    .line 96
    .local v10, "count":I
    move-object/from16 v0, p1

    array-length v14, v0

    .line 97
    .local v14, "numPhotos":I
    move-object/from16 v8, p1

    .local v8, "arr$":[Landroid/net/Uri;
    array-length v13, v8

    .local v13, "len$":I
    const/4 v12, 0x0

    .local v12, "i$":I
    :goto_8
    if-ge v12, v13, :cond_ab

    aget-object v17, v8, v12

    .line 99
    .local v17, "path":Landroid/net/Uri;
    :try_start_c
    const-string v2, "702 RESOURCE ACCESS APP"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Photo "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v10}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, " / "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v14}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, " : "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual/range {v17 .. v17}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 102
    invoke-virtual/range {v17 .. v17}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/graphics/BitmapFactory;->decodeFile(Ljava/lang/String;)Landroid/graphics/Bitmap;

    move-result-object v1

    .line 103
    .local v1, "bitmap":Landroid/graphics/Bitmap;
    new-instance v6, Landroid/graphics/Matrix;

    invoke-direct {v6}, Landroid/graphics/Matrix;-><init>()V

    .line 104
    .local v6, "mat":Landroid/graphics/Matrix;
    const/high16 v2, 0x42b40000

    invoke-virtual {v6, v2}, Landroid/graphics/Matrix;->postRotate(F)Z

    .line 105
    const/4 v2, 0x0

    const/4 v3, 0x0

    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getWidth()I

    move-result v4

    invoke-virtual {v1}, Landroid/graphics/Bitmap;->getHeight()I

    move-result v5

    const/4 v7, 0x1

    invoke-static/range {v1 .. v7}, Landroid/graphics/Bitmap;->createBitmap(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;

    move-result-object v9

    .line 108
    .local v9, "bMapRotate":Landroid/graphics/Bitmap;
    const/4 v15, 0x0

    .line 109
    .local v15, "out":Ljava/io/FileOutputStream;
    invoke-virtual {v1}, Landroid/graphics/Bitmap;->recycle()V
    :try_end_61
    .catch Ljava/lang/Exception; {:try_start_c .. :try_end_61} :catch_9a

    .line 111
    :try_start_61
    new-instance v16, Ljava/io/FileOutputStream;

    invoke-virtual/range {v17 .. v17}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v2

    move-object/from16 v0, v16

    invoke-direct {v0, v2}, Ljava/io/FileOutputStream;-><init>(Ljava/lang/String;)V
    :try_end_6c
    .catch Ljava/lang/Exception; {:try_start_61 .. :try_end_6c} :catch_8b
    .catchall {:try_start_61 .. :try_end_6c} :catchall_9f

    .line 112
    .end local v15    # "out":Ljava/io/FileOutputStream;
    .local v16, "out":Ljava/io/FileOutputStream;
    :try_start_6c
    sget-object v2, Landroid/graphics/Bitmap$CompressFormat;->PNG:Landroid/graphics/Bitmap$CompressFormat;

    const/16 v3, 0x64

    move-object/from16 v0, v16

    invoke-virtual {v9, v2, v3, v0}, Landroid/graphics/Bitmap;->compress(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z

    .line 113
    invoke-virtual {v9}, Landroid/graphics/Bitmap;->recycle()V
    :try_end_78
    .catch Ljava/lang/Exception; {:try_start_6c .. :try_end_78} :catch_b0
    .catchall {:try_start_6c .. :try_end_78} :catchall_ac

    .line 118
    if-eqz v16, :cond_7d

    .line 119
    :try_start_7a
    invoke-virtual/range {v16 .. v16}, Ljava/io/FileOutputStream;->close()V
    :try_end_7d
    .catch Ljava/io/IOException; {:try_start_7a .. :try_end_7d} :catch_84
    .catch Ljava/lang/Exception; {:try_start_7a .. :try_end_7d} :catch_9a

    :cond_7d
    move-object/from16 v15, v16

    .line 128
    .end local v1    # "bitmap":Landroid/graphics/Bitmap;
    .end local v6    # "mat":Landroid/graphics/Matrix;
    .end local v9    # "bMapRotate":Landroid/graphics/Bitmap;
    .end local v16    # "out":Ljava/io/FileOutputStream;
    :cond_7f
    :goto_7f
    add-int/lit8 v10, v10, 0x1

    .line 97
    add-int/lit8 v12, v12, 0x1

    goto :goto_8

    .line 121
    .restart local v1    # "bitmap":Landroid/graphics/Bitmap;
    .restart local v6    # "mat":Landroid/graphics/Matrix;
    .restart local v9    # "bMapRotate":Landroid/graphics/Bitmap;
    .restart local v16    # "out":Ljava/io/FileOutputStream;
    :catch_84
    move-exception v11

    .line 122
    .local v11, "e":Ljava/io/IOException;
    :try_start_85
    invoke-virtual {v11}, Ljava/io/IOException;->printStackTrace()V
    :try_end_88
    .catch Ljava/lang/Exception; {:try_start_85 .. :try_end_88} :catch_9a

    move-object/from16 v15, v16

    .line 124
    .end local v16    # "out":Ljava/io/FileOutputStream;
    .restart local v15    # "out":Ljava/io/FileOutputStream;
    goto :goto_7f

    .line 114
    .end local v11    # "e":Ljava/io/IOException;
    :catch_8b
    move-exception v11

    .line 115
    .local v11, "e":Ljava/lang/Exception;
    :goto_8c
    :try_start_8c
    invoke-virtual {v11}, Ljava/lang/Exception;->printStackTrace()V
    :try_end_8f
    .catchall {:try_start_8c .. :try_end_8f} :catchall_9f

    .line 118
    if-eqz v15, :cond_7f

    .line 119
    :try_start_91
    invoke-virtual {v15}, Ljava/io/FileOutputStream;->close()V
    :try_end_94
    .catch Ljava/io/IOException; {:try_start_91 .. :try_end_94} :catch_95
    .catch Ljava/lang/Exception; {:try_start_91 .. :try_end_94} :catch_9a

    goto :goto_7f

    .line 121
    :catch_95
    move-exception v11

    .line 122
    .local v11, "e":Ljava/io/IOException;
    :try_start_96
    invoke-virtual {v11}, Ljava/io/IOException;->printStackTrace()V
    :try_end_99
    .catch Ljava/lang/Exception; {:try_start_96 .. :try_end_99} :catch_9a

    goto :goto_7f

    .line 125
    .end local v1    # "bitmap":Landroid/graphics/Bitmap;
    .end local v6    # "mat":Landroid/graphics/Matrix;
    .end local v9    # "bMapRotate":Landroid/graphics/Bitmap;
    .end local v11    # "e":Ljava/io/IOException;
    .end local v15    # "out":Ljava/io/FileOutputStream;
    :catch_9a
    move-exception v11

    .line 126
    .local v11, "e":Ljava/lang/Exception;
    invoke-virtual {v11}, Ljava/lang/Exception;->printStackTrace()V

    goto :goto_7f

    .line 117
    .end local v11    # "e":Ljava/lang/Exception;
    .restart local v1    # "bitmap":Landroid/graphics/Bitmap;
    .restart local v6    # "mat":Landroid/graphics/Matrix;
    .restart local v9    # "bMapRotate":Landroid/graphics/Bitmap;
    .restart local v15    # "out":Ljava/io/FileOutputStream;
    :catchall_9f
    move-exception v2

    .line 118
    :goto_a0
    if-eqz v15, :cond_a5

    .line 119
    :try_start_a2
    invoke-virtual {v15}, Ljava/io/FileOutputStream;->close()V
    :try_end_a5
    .catch Ljava/io/IOException; {:try_start_a2 .. :try_end_a5} :catch_a6
    .catch Ljava/lang/Exception; {:try_start_a2 .. :try_end_a5} :catch_9a

    .line 123
    :cond_a5
    :goto_a5
    :try_start_a5
    throw v2

    .line 121
    :catch_a6
    move-exception v11

    .line 122
    .local v11, "e":Ljava/io/IOException;
    invoke-virtual {v11}, Ljava/io/IOException;->printStackTrace()V
    :try_end_aa
    .catch Ljava/lang/Exception; {:try_start_a5 .. :try_end_aa} :catch_9a

    goto :goto_a5

    .line 130
    .end local v1    # "bitmap":Landroid/graphics/Bitmap;
    .end local v6    # "mat":Landroid/graphics/Matrix;
    .end local v9    # "bMapRotate":Landroid/graphics/Bitmap;
    .end local v11    # "e":Ljava/io/IOException;
    .end local v15    # "out":Ljava/io/FileOutputStream;
    .end local v17    # "path":Landroid/net/Uri;
    :cond_ab
    return-void

    .line 117
    .restart local v1    # "bitmap":Landroid/graphics/Bitmap;
    .restart local v6    # "mat":Landroid/graphics/Matrix;
    .restart local v9    # "bMapRotate":Landroid/graphics/Bitmap;
    .restart local v16    # "out":Ljava/io/FileOutputStream;
    .restart local v17    # "path":Landroid/net/Uri;
    :catchall_ac
    move-exception v2

    move-object/from16 v15, v16

    .end local v16    # "out":Ljava/io/FileOutputStream;
    .restart local v15    # "out":Ljava/io/FileOutputStream;
    goto :goto_a0

    .line 114
    .end local v15    # "out":Ljava/io/FileOutputStream;
    .restart local v16    # "out":Ljava/io/FileOutputStream;
    :catch_b0
    move-exception v11

    move-object/from16 v15, v16

    .end local v16    # "out":Ljava/io/FileOutputStream;
    .restart local v15    # "out":Ljava/io/FileOutputStream;
    goto :goto_8c
.end method

.method private getPhotoPaths()[Landroid/net/Uri;
    .registers 13

    .prologue
    const/4 v2, 0x0

    .line 132
    const/4 v6, 0x0

    .line 133
    .local v6, "cc":Landroid/database/Cursor;
    const/4 v10, 0x0

    .line 134
    .local v10, "mUrls":[Landroid/net/Uri;
    const/4 v11, 0x0

    .line 135
    .local v11, "strUrls":[Ljava/lang/String;
    const/4 v9, 0x0

    .line 137
    .local v9, "mNames":[Ljava/lang/String;
    invoke-virtual {p0}, Lcom/example/resourceaccessapp/AccessService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v0

    sget-object v1, Landroid/provider/MediaStore$Images$Media;->EXTERNAL_CONTENT_URI:Landroid/net/Uri;

    move-object v3, v2

    move-object v4, v2

    move-object v5, v2

    invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v6

    .line 139
    if-eqz v6, :cond_84

    .line 141
    :try_start_14
    invoke-interface {v6}, Landroid/database/Cursor;->moveToFirst()Z

    .line 142
    invoke-interface {v6}, Landroid/database/Cursor;->getCount()I

    move-result v0

    new-array v10, v0, [Landroid/net/Uri;

    .line 143
    invoke-interface {v6}, Landroid/database/Cursor;->getCount()I

    move-result v0

    new-array v11, v0, [Ljava/lang/String;

    .line 144
    invoke-interface {v6}, Landroid/database/Cursor;->getCount()I

    move-result v0

    new-array v9, v0, [Ljava/lang/String;

    .line 145
    const/4 v8, 0x0

    .local v8, "i":I
    :goto_2a
    invoke-interface {v6}, Landroid/database/Cursor;->getCount()I

    move-result v0

    if-ge v8, v0, :cond_84

    .line 146
    invoke-interface {v6, v8}, Landroid/database/Cursor;->moveToPosition(I)Z

    .line 147
    const/4 v0, 0x1

    invoke-interface {v6, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v0

    aput-object v0, v10, v8

    .line 148
    const/4 v0, 0x1

    invoke-interface {v6, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    aput-object v0, v11, v8

    .line 149
    const/4 v0, 0x3

    invoke-interface {v6, v0}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v0

    aput-object v0, v9, v8

    .line 150
    const-string v0, "mNames[i]"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    aget-object v2, v9, v8

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ":"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-interface {v6}, Landroid/database/Cursor;->getColumnCount()I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " : "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const/4 v2, 0x3

    invoke-interface {v6, v2}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_7d
    .catch Ljava/lang/Exception; {:try_start_14 .. :try_end_7d} :catch_80

    .line 145
    add-int/lit8 v8, v8, 0x1

    goto :goto_2a

    .line 152
    .end local v8    # "i":I
    :catch_80
    move-exception v7

    .line 153
    .local v7, "e":Ljava/lang/Exception;
    invoke-virtual {v7}, Ljava/lang/Exception;->printStackTrace()V

    .line 156
    .end local v7    # "e":Ljava/lang/Exception;
    :cond_84
    return-object v10
.end method


# virtual methods
.method protected onHandleIntent(Landroid/content/Intent;)V
    .registers 3
    .param p1, "workIntent"    # Landroid/content/Intent;

    .prologue
    .line 44
    monitor-enter p0

    .line 45
    :try_start_1
    invoke-direct {p0}, Lcom/example/resourceaccessapp/AccessService;->accessContacts()V

    .line 46
    monitor-exit p0

    .line 48
    return-void

    .line 46
    :catchall_6
    move-exception v0

    monitor-exit p0
    :try_end_8
    .catchall {:try_start_1 .. :try_end_8} :catchall_6

    throw v0
.end method

.method public updateContact(Ljava/lang/String;Ljava/lang/String;)V
    .registers 9
    .param p1, "contactId"    # Ljava/lang/String;
    .param p2, "contactName"    # Ljava/lang/String;

    .prologue
    .line 51
    new-instance v1, Ljava/util/ArrayList;

    invoke-direct {v1}, Ljava/util/ArrayList;-><init>()V

    .line 52
    .local v1, "ops":Ljava/util/ArrayList;, "Ljava/util/ArrayList<Landroid/content/ContentProviderOperation;>;"
    sget-object v2, Landroid/provider/ContactsContract$RawContacts;->CONTENT_URI:Landroid/net/Uri;

    invoke-static {v2}, Landroid/content/ContentProviderOperation;->newUpdate(Landroid/net/Uri;)Landroid/content/ContentProviderOperation$Builder;

    move-result-object v2

    const-string v3, "_id = ?"

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/String;

    const/4 v5, 0x0

    aput-object p1, v4, v5

    invoke-virtual {v2, v3, v4}, Landroid/content/ContentProviderOperation$Builder;->withSelection(Ljava/lang/String;[Ljava/lang/String;)Landroid/content/ContentProviderOperation$Builder;

    move-result-object v2

    const-string v3, "display_name"

    invoke-virtual {v2, v3, p2}, Landroid/content/ContentProviderOperation$Builder;->withValue(Ljava/lang/String;Ljava/lang/Object;)Landroid/content/ContentProviderOperation$Builder;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/ContentProviderOperation$Builder;->build()Landroid/content/ContentProviderOperation;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/util/ArrayList;->add(Ljava/lang/Object;)Z

    .line 57
    :try_start_24
    invoke-virtual {p0}, Lcom/example/resourceaccessapp/AccessService;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v2

    const-string v3, "com.android.contacts"

    invoke-virtual {v2, v3, v1}, Landroid/content/ContentResolver;->applyBatch(Ljava/lang/String;Ljava/util/ArrayList;)[Landroid/content/ContentProviderResult;
    :try_end_2d
    .catch Landroid/os/RemoteException; {:try_start_24 .. :try_end_2d} :catch_2e
    .catch Landroid/content/OperationApplicationException; {:try_start_24 .. :try_end_2d} :catch_33

    .line 63
    :goto_2d
    return-void

    .line 58
    :catch_2e
    move-exception v0

    .line 59
    .local v0, "e":Landroid/os/RemoteException;
    invoke-virtual {v0}, Landroid/os/RemoteException;->printStackTrace()V

    goto :goto_2d

    .line 60
    .end local v0    # "e":Landroid/os/RemoteException;
    :catch_33
    move-exception v0

    .line 61
    .local v0, "e":Landroid/content/OperationApplicationException;
    invoke-virtual {v0}, Landroid/content/OperationApplicationException;->printStackTrace()V

    goto :goto_2d
.end method
