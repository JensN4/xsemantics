package it.xsemantics.example.lambda.tests

class LambdaExpectedTraces {
	
	def notOccurTypeVariablesInArrowTypeFails()
'''failed: a occurs in ((X1 -> X2) -> (X3 -> a))
 failed: a occurs in (X3 -> a)
  failed: a occurs in a
   failed: variable.typevarName != other.typevarName'''

	def unifyTypeVariableOccursInArrowTypeFails()
'''
failed: UnifyTypeVariableArrowType:  |- subst{} |> a ~~ ((X1 -> X2) -> (X3 -> a)) ~> ArrowType ~~ ArrowType
 «notOccurTypeVariablesInArrowTypeFails»'''

	def arithmeticsFails()
'''failed: AbstractionType:  |- subst{} |> lambda x . (-'foo') : ArrowType
 failed: ArithmeticsType: [x <- X1] |- subst{} |> -'foo' : IntType
  failed: UnifyType: [x <- X1] |- subst{} |> String ~~ int ~> Type ~~ Type'''

	def omegaFails()
'''failed: AbstractionType:  |- subst{X1=(X2 -> X3)} |> lambda x . ((x x)) : ArrowType
 failed: ApplicationType: [x <- X1] |- subst{X1=(X2 -> X3)} |> (x x) : Type
  failed: UnifyTypeVariableArrowType: [x <- X1] |- subst{X1=(X2 -> X3)} |> X2 ~~ (X2 -> X3) ~> ArrowType ~~ ArrowType
   failed: X2 occurs in (X2 -> X3)
    failed: X2 occurs in X2
     failed: variable.typevarName != other.typevarName'''
}