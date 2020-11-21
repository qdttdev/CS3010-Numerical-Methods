package com.company;
import java.util.ArrayList;

public class Polynomial
{
    public ArrayList<Double> multiplyX(ArrayList<Double> array)
    {
        ArrayList<Double> value = new ArrayList<Double>();
        value.add(0.0);

        for(int i = 0; i < array.size() - 1; i++){
            value.add(array.get(i));
        }
        return value;
    }

    public ArrayList<Double> multiplyConstants(ArrayList<Double> array, double x)
    {
        ArrayList<Double> value = new ArrayList<Double>();
        for(double i : array){
            value.add(i * x);
        }
        return value;
    }

    public ArrayList<Double> combineLikeTerms(ArrayList<ArrayList<Double>> array)
    {
        ArrayList<Double> combined = new ArrayList<Double>();
        double sum = 0;

        for(int i = 0; i < array.get(0).size(); i++)
        {
            sum = 0;

            for (ArrayList<Double> doubles : array)
            {
                sum = sum + doubles.get(i);
            }

            combined.add(sum);
        }

        return combined;
    }

    public ArrayList<Double> polyFunction(double value, ArrayList<Double> array, int size)
    {
        ArrayList<ArrayList<Double>> a = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> y = new ArrayList<Double>();
        int ySize = 0;

        for(int i = 0; i < array.size() + 1; i++)
        {
            y.add(0.0);
        }

        y.add(0, value);

        for (Double aDouble : array)
        {
            a.add(multiplyX(y));
            a.add(multiplyConstants(y, -aDouble));
            y = combineLikeTerms(a);
            a.clear();
        }

        ySize = y.size();

        for(int i = 0; i < size - ySize; i++)
        {
            y.add(0.0);
        }

        return y;
    }
}
