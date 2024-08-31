using System.Data.Common;
using Microsoft.Data.Sqlite;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.Tests.Common;

public sealed class TestDBContextFactory<T> : IDisposable where T : DbContext
{
    private DbConnection? _connection;

    public T CreateContext()
    {
        CreateConnection();
        var context = Activator.CreateInstance(typeof(T), CreateOptions()) as T ?? throw new Exception("Failed to activate test DBContext");
        context.Database.EnsureCreated();
        return context;
    }

    private void CreateConnection()
    {
        if (_connection == null)
        {
            _connection = new SqliteConnection("DataSource=:memory:");
            _connection.Open();
        }
    }

    private DbContextOptions<T> CreateOptions()
    {
        return new DbContextOptionsBuilder<T>()
            .UseSqlite(_connection)
            .EnableSensitiveDataLogging()
            .Options;
    }

    void IDisposable.Dispose()
    {
        if (_connection != null)
        {
            _connection.Dispose();
            _connection = null;
        }
    }
}