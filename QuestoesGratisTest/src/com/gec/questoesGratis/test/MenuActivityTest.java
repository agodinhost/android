
package com.gec.questoesGratis.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.gec.questoesGratis.ApplicationX;
import com.gec.questoesGratis.MenuActivity;

public class MenuActivityTest extends ActivityInstrumentationTestCase2< MenuActivity > {

   private Activity mActivity;

   public MenuActivityTest() {
      this( MenuActivity.class.getName() );
   }

   public MenuActivityTest( String name ) {
      super( MenuActivity.class );
      setName( name );
   }

   protected void setUp() throws Exception {
      super.setUp();
      mActivity = getActivity();
   }

   protected void tearDown() throws Exception {
      super.tearDown();
   }

   public final void testPreconditions() {
      assertNotNull( mActivity );
   }

   public void testGetQuizzes() {
      assertTrue( ApplicationX.getInstance().getQuizzes().size() > 0 );
   }
}
