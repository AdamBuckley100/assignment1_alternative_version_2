import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;

//------------------------------------------------------------
//  Template for the Lempel-Ziv compression/uncompression 
//  program.
//
//  Written by Mark Allen Weiss, modified by Christina Leslie
//------------------------------------------------------------

interface BitUtils
{
    public static final int BITS_PER_BYTES = 8;

}

// BitInputStream class: Bit-input stream wrapper class.
//
// CONSTRUCTION: with an open InputStream.
//
// ******************PUBLIC OPERATIONS***********************
// int readBit( )              --> Read one bit as a 0 or 1
// void close( )               --> Close underlying stream

class BitInputStream
{
    public BitInputStream( InputStream is )
    {
        in = is;
        bufferPos = BitUtils.BITS_PER_BYTES;
    }
    
    public int readBit( ) throws IOException
    {
        if ( bufferPos == BitUtils.BITS_PER_BYTES )
        {
            buffer = in.read( );
            if( buffer == -1 )
                return -1;
            bufferPos = 0;
        }
        
        return getBit( buffer, bufferPos++ );    
    }
    
    public void close( ) throws IOException
    {
        in.close( );
    }
    
    private static int getBit( int pack, int pos )
    {
        return ( pack & ( 1 << pos ) ) != 0 ? 1 : 0;
    }
    
    private InputStream in;
    private int buffer;
    private int bufferPos;
}    

// BitOutputStream class: Bit-output stream wrapper class.
//
// CONSTRUCTION: with an open OutputStream.
//
// ******************PUBLIC OPERATIONS***********************
// void writeBit( val )        --> Write one bit (0 or 1)
// void writeBits( vald )      --> Write array of bits
// void flush( )               --> Flush buffered bits
// void close( )               --> Close underlying stream

class BitOutputStream
{
    public BitOutputStream( OutputStream os )
    {
        bufferPos = 0;
        buffer = 0;
        out = os;
    }
    
    public void writeBit( int val ) throws IOException
    {
        buffer = setBit( buffer, bufferPos++, val );
        if( bufferPos == BitUtils.BITS_PER_BYTES )
            flush( );
    }
    
    public void writeBits( int [ ] val ) throws IOException
    {
        for( int i = 0; i < val.length; i++ )
            writeBit( val[ i ] );
    }    
    
    public void flush( ) throws IOException
    {
        if( bufferPos == 0 )
            return;
        
        out.write( buffer );
        bufferPos = 0;
        buffer = 0;    
    }
    
    public void close( ) throws IOException
    {
        flush( );
        out.close( );
    }
    
    private int setBit( int pack, int pos, int val )
    {
        if( val == 1 )
            pack |= ( val << pos );
        return pack;
    }
    
    private OutputStream out;
    private int buffer;
    private int bufferPos;
}

public class Decompresser
{

    public static void compress( String inFile ) throws IOException
    {
        String compressedFile = inFile + ".lz";
        InputStream in = new BufferedInputStream(
                         new FileInputStream( inFile ) );
        OutputStream fout = new BufferedOutputStream(
                            new FileOutputStream( compressedFile ) );
	BitOutputStream bout = new BitOutputStream(fout);

	// read input file into a byte array stream
	ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        int ch;
        while( ( ch = in.read( ) ) != -1 )
            byteOut.write( ch );
        in.close( );

	// now convert the input file to an array of bytes in memory
	byte [ ] theInput = byteOut.toByteArray( );

	//----------------------------------------------------------
	// Your compression routine goes here!
	//
	// Notes: 
	//   - bout is a BitOutputStream object; you can use its
	// instance methods to write bits.
	// 
	//   - the code above reads the input file into an array of
	// bytes in memory called theInput
	//
	//   - convert byte to char via an explicit cast, e.g.:
	// char firstchar = (char) theInput[0];
	//----------------------------------------------------------

	bout.close();
    }
        
    public static void uncompress( String compressedFile ) throws IOException
    {
        String inFile;
        String extension;
        
        inFile = compressedFile.substring( 0, compressedFile.length( ) - 3 );
        extension = compressedFile.substring( compressedFile.length( ) - 3 );
        
        if( !extension.equals( ".lz" ) )
        {
            System.out.println( "Not a compressed file!" );
            return;
        }
        
        inFile += ".uc";    // for debugging, so as to not clobber original
        InputStream fin = new BufferedInputStream(
                          new FileInputStream( compressedFile ) );
        DataInputStream in = new DataInputStream( fin );
        BitInputStream bin = new BitInputStream( in );
        
        OutputStream fout = new BufferedOutputStream(
                            new FileOutputStream( inFile ) );
          
	//-------------------------------------------------------
	// Your uncompression routine goes here!
	//
	// Note:
	//   - bin is a BitInputStream object; you can use its
	// methods to read bits from the compressed input file
	//-------------------------------------------------------

        bin.close( );
        fout.close( );
    }

    public static void main( String [ ] args ) throws IOException
    {
        if( args.length < 2 )
        {
            System.out.println( "Usage: java LempelZiv -[cu] files" );
            return;
        }
        
        String option = args[ 0 ];
        for( int i = 1; i < args.length; i++ )
        {
            String nextFile = args[ i ];
            if( option.equals( "-c" ) )
                compress( nextFile );
            else if( option.equals( "-u" ) )
                uncompress( nextFile );
            else
            {
                System.out.println( "Usage: java LempelZiv -[cu] files" );
                return;
            }
        }
    }
}
