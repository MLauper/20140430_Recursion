package expr;
/**
   A class that can compute the value of an arithmetic expression.
*/
public class Evaluator
{

	private ExpressionTokenizer tokenizer;	
	
	/**
      Constructs an evaluator.
      @param anExpression a string containing the expression
      to be evaluated
   */
   public Evaluator(String anExpression)
   {
      tokenizer = new ExpressionTokenizer(anExpression);
   }

   /**
      Evaluates the expression.
      @return the value of the expression.
   */
   public double getExpressionValue()
   {
      double value = getTermValue();

      while (true)
      {
         String next = this.tokenizer.peekToken();
         if ("+".equals(next) || "-".equals(next))
         {
            this.tokenizer.nextToken(); // Discard "+" or "-"
            double rightValue = this.getTermValue();

            if ("+".equals(next)){
                value = value + rightValue;
            }
            else {
                value = value - rightValue;
            }
         }
         else {
             return value;
         }
      }
   }

   /**
      Evaluates the next term found in the expression.
      @return the value of the term
   */
   public double getTermValue()
   {
      double value = getPowerValue();
      while (true)
      {
         String next = tokenizer.peekToken();

         if ("*".equals(next) || "/".equals(next))
         {
            tokenizer.nextToken();
            double value2 = getPowerValue();
            if ("*".equals(next)) value = value * value2;
            else value = value / value2;
         }
         else {
             return value;
         }
      }
   }

    public double getPowerValue()
    {
        double value = getFactorValue();
        while (true)
        {
            String next = tokenizer.peekToken();
            if ("^".equals(next))
            {
                tokenizer.nextToken();
                double value2 = getFactorValue();
                value = Math.pow(value,value2);
            }
            else {
                return value;
            }
        }
    }

   /**
      Evaluates the next factor found in the expression.
      @return the value of the factor
   */
   public double getFactorValue()
   {
      String next = tokenizer.peekToken();
      if ("(".equals(next))
      {
         tokenizer.nextToken(); // Discard "("
         double value = getExpressionValue();
         tokenizer.nextToken(); // Discard ")"

         return value;
      }
      else {
          return Integer.parseInt(tokenizer.nextToken());
      }
   }
}