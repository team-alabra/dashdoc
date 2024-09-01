using System.Data.Common;
using Dashdoc.API.Data;
using Microsoft.Data.Sqlite;
using Microsoft.EntityFrameworkCore;

namespace Dashdoc.Tests.Common;

public sealed class TestDBContextFactory : IDisposable
{
    private DbConnection? _connection;

    public DashdocDbContext CreateContext()
    {
        CreateConnection();
        var context = Activator.CreateInstance(typeof(DashdocDbContext), CreateOptions()) as DashdocDbContext  ?? throw new Exception("Failed to activate test DBContext");
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

    private DbContextOptions<DashdocDbContext> CreateOptions()
    {
        return new DbContextOptionsBuilder<DashdocDbContext>()
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