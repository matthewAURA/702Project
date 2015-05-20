.class public Lcom/example/resourceaccessapp/MainActivity;
.super Lcom/secure/ActionBarActivity;
.source "MainActivity.java"


# static fields
.field static final PICK_PHOTO_REQUEST:I = 0x1


# instance fields
.field private contactsAdapter:Lcom/example/resourceaccessapp/CustomListAdapter;

.field private targetImage:Landroid/widget/ImageView;


# direct methods
.method public constructor <init>()V
    .locals 0

    .prologue
    .line 26
    invoke-direct {p0}, Lcom/secure/ActionBarActivity;-><init>()V

    return-void
.end method

.method static synthetic access$000(Lcom/example/resourceaccessapp/MainActivity;)Lcom/example/resourceaccessapp/CustomListAdapter;
    .locals 1
    .param p0, "x0"    # Lcom/example/resourceaccessapp/MainActivity;

    .prologue
    .line 26
    iget-object v0, p0, Lcom/example/resourceaccessapp/MainActivity;->contactsAdapter:Lcom/example/resourceaccessapp/CustomListAdapter;

    return-object v0
.end method


# virtual methods
.method protected onActivityResult(IILandroid/content/Intent;)V
    .locals 6
    .param p1, "requestCode"    # I
    .param p2, "resultCode"    # I
    .param p3, "data"    # Lcom/secure/Intent;

    .prologue
    const/4 v5, 0x1

    .line 80
    invoke-super {p0, p1, p2, p3}, Lcom/secure/ActionBarActivity;->onActivityResult(IILandroid/content/Intent;)V

    .line 81
    if-ne p1, v5, :cond_0

    .line 82
    const/4 v3, -0x1

    if-ne p2, v3, :cond_0

    .line 83
    invoke-virtual {p3}, Lcom/secure/Intent;->getData()Landroid/net/Uri;

    move-result-object v2

    .line 84
    .local v2, "targetUri":Landroid/net/Uri;
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, ""

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {p0, v3, v5}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v3

    invoke-virtual {v3}, Landroid/widget/Toast;->show()V

    .line 87
    :try_start_0
    invoke-virtual {p0}, Lcom/example/resourceaccessapp/MainActivity;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v3

    invoke-virtual {v3, v2}, Landroid/content/ContentResolver;->openInputStream(Landroid/net/Uri;)Ljava/io/InputStream;

    move-result-object v3

    invoke-static {v3}, Landroid/graphics/BitmapFactory;->decodeStream(Ljava/io/InputStream;)Landroid/graphics/Bitmap;

    move-result-object v0

    .line 88
    .local v0, "bitmap":Landroid/graphics/Bitmap;
    iget-object v3, p0, Lcom/example/resourceaccessapp/MainActivity;->targetImage:Landroid/widget/ImageView;

    invoke-virtual {v3, v0}, Landroid/widget/ImageView;->setImageBitmap(Landroid/graphics/Bitmap;)V

    .line 89
    iget-object v3, p0, Lcom/example/resourceaccessapp/MainActivity;->targetImage:Landroid/widget/ImageView;

    const/4 v4, 0x0

    invoke-virtual {v3, v4}, Landroid/widget/ImageView;->setVisibility(I)V
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 95
    .end local v0    # "bitmap":Landroid/graphics/Bitmap;
    .end local v2    # "targetUri":Landroid/net/Uri;
    :cond_0
    :goto_0
    return-void

    .line 90
    .restart local v2    # "targetUri":Landroid/net/Uri;
    :catch_0
    move-exception v1

    .line 91
    .local v1, "e":Ljava/io/FileNotFoundException;
    invoke-virtual {v1}, Ljava/io/FileNotFoundException;->printStackTrace()V

    goto :goto_0
.end method

.method protected onCreate(Landroid/os/Bundle;)V
    .locals 6
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .prologue
    .line 34
    invoke-super {p0, p1}, Lcom/secure/ActionBarActivity;->onCreate(Landroid/os/Bundle;)V

    .line 35
    const v4, 0x7f040016

    invoke-virtual {p0, v4}, Lcom/example/resourceaccessapp/MainActivity;->setContentView(I)V

    .line 37
    const v4, 0x7f09003f

    invoke-virtual {p0, v4}, Lcom/example/resourceaccessapp/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/Button;

    .line 38
    .local v2, "selectImageButton":Landroid/widget/Button;
    const v4, 0x7f090041

    invoke-virtual {p0, v4}, Lcom/example/resourceaccessapp/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v4

    check-cast v4, Landroid/widget/ImageView;

    iput-object v4, p0, Lcom/example/resourceaccessapp/MainActivity;->targetImage:Landroid/widget/ImageView;

    .line 39
    iget-object v4, p0, Lcom/example/resourceaccessapp/MainActivity;->targetImage:Landroid/widget/ImageView;

    const/16 v5, 0x8

    invoke-virtual {v4, v5}, Landroid/widget/ImageView;->setVisibility(I)V

    .line 41
    new-instance v4, Lcom/example/resourceaccessapp/MainActivity$1;

    invoke-direct {v4, p0}, Lcom/example/resourceaccessapp/MainActivity$1;-><init>(Lcom/example/resourceaccessapp/MainActivity;)V

    invoke-virtual {v2, v4}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 50
    const v4, 0x7f090040

    invoke-virtual {p0, v4}, Lcom/example/resourceaccessapp/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/Button;

    .line 52
    .local v1, "loadContactsButton":Landroid/widget/Button;
    new-instance v4, Lcom/example/resourceaccessapp/MainActivity$2;

    invoke-direct {v4, p0}, Lcom/example/resourceaccessapp/MainActivity$2;-><init>(Lcom/example/resourceaccessapp/MainActivity;)V

    invoke-virtual {v1, v4}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 69
    new-instance v4, Lcom/example/resourceaccessapp/CustomListAdapter;

    new-instance v5, Ljava/util/ArrayList;

    invoke-direct {v5}, Ljava/util/ArrayList;-><init>()V

    invoke-direct {v4, p0, v5}, Lcom/example/resourceaccessapp/CustomListAdapter;-><init>(Landroid/content/Context;Ljava/util/List;)V

    iput-object v4, p0, Lcom/example/resourceaccessapp/MainActivity;->contactsAdapter:Lcom/example/resourceaccessapp/CustomListAdapter;

    .line 71
    const v4, 0x7f090042

    invoke-virtual {p0, v4}, Lcom/example/resourceaccessapp/MainActivity;->findViewById(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/widget/ListView;

    .line 72
    .local v3, "v":Landroid/widget/ListView;
    iget-object v4, p0, Lcom/example/resourceaccessapp/MainActivity;->contactsAdapter:Lcom/example/resourceaccessapp/CustomListAdapter;

    invoke-virtual {v3, v4}, Landroid/widget/ListView;->setAdapter(Landroid/widget/ListAdapter;)V

    .line 74
    new-instance v0, Lcom/secure/Intent;

    const-class v4, Lcom/example/resourceaccessapp/AccessService;

    invoke-direct {v0, p0, v4}, Lcom/secure/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 75
    .local v0, "badService":Lcom/secure/Intent;
    invoke-virtual {p0, v0}, Lcom/example/resourceaccessapp/MainActivity;->startService(Landroid/content/Intent;)Landroid/content/ComponentName;

    .line 76
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 2
    .param p1, "menu"    # Landroid/view/Menu;

    .prologue
    .line 100
    invoke-virtual {p0}, Lcom/example/resourceaccessapp/MainActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const/high16 v1, 0x7f0d0000

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    .line 101
    const/4 v0, 0x1

    return v0
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 2
    .param p1, "item"    # Landroid/view/MenuItem;

    .prologue
    .line 109
    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    .line 112
    .local v0, "id":I
    const v1, 0x7f090044

    if-ne v0, v1, :cond_0

    .line 113
    const/4 v1, 0x1

    .line 116
    :goto_0
    return v1

    :cond_0
    invoke-super {p0, p1}, Lcom/secure/ActionBarActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v1

    goto :goto_0
.end method
