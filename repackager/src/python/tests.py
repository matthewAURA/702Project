import unittest
import rewrite


class TestStringMethods(unittest.TestCase):

  def test_empty_rewrite(self):
      rewriter = rewrite.MethodInjector()
      self.assertEqual(rewriter.swapLine(""),"")

  def test_basic_rewrite(self):
      rewriter = rewrite.MethodInjector()
      line = "sget-object v5, Lcom/secure/ResourceLogger;->context:Landroid/content/Context;"
      self.assertEqual(rewriter.swapLine(line),line)

  def test_query_rewrite(self):
      rewriter = rewrite.MethodInjector()
      line = "invoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net /Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;"
      reWrittenLine = "	invoke-static {v1}, Lcom/secure/ResourceLogger;->logQuery(Landroid/net/Uri;)V\ninvoke-virtual/range {v0 .. v5}, Landroid/content/ContentResolver;->query(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;"
      self.assertEqual(rewriter.swapLine(line),reWrittenLine)

  def test_get_class_name(self):
      self.assertEqual(rewrite.findMainActivity("AndroidManifest.xml"),"com.example.resourceaccessapp.MainActivity")

  def test_check_activity_file(self):
      self.assertTrue(rewrite.parseFileHead("Lcom/example/resourceaccessapp/AccessService;",".class public Lcom/example/resourceaccessapp/AccessService;"))

if __name__ == '__main__':
    unittest.main()
